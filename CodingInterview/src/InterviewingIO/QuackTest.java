package InterviewingIO;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuackTest {
  @Test
  public void emptyQuack() {
    Quack<String> quack = new Quack<>(String.class, 10);
    System.out.println(quack);
    assertEquals(quack.toString(), "[]");
  }

  @Test
  public void pushesAndPops() {
    Quack<Integer> quack = new Quack<>(Integer.class, 10);

    for (int i = 1; i <= 10; i++) {
      quack.push(i);
      System.out.println(quack);
      assertEquals(quack.currentSize(), i);
    }
    assertEquals(quack.toString(), "[1 2 3 4 5 6 7 8 9 10]");

    for (int i = 10; i >= 1; i--) {
      int popped = quack.pop();
      System.out.println(quack);
      assertEquals(popped, i);
      assertEquals(quack.currentSize(), i - 1);
    }

    assertEquals(quack.toString(), "[]");
    assertEquals(quack.currentSize(), 0);
  }

  @Test
  public void enqueuesAndDequeues() {
    Quack<Integer> quack = new Quack<>(Integer.class, 10);

    for (int i = 1; i <= 10; i++) {
      quack.enqueue(i);
      System.out.println(quack);
      assertEquals(quack.currentSize(), i);
    }
    assertEquals(quack.toString(), "[1 2 3 4 5 6 7 8 9 10]");

    for (int i = 1; i <= 10; i++) {
      int dq = quack.dequeue();
      System.out.println(quack);
      assertEquals(dq, i);
      assertEquals(quack.currentSize(), 10 - i);
    }

    assertEquals(quack.toString(), "[]");
    assertEquals(quack.currentSize(), 0);
  }

  @Test
  public void mixture() {
    Quack<Integer> quack = new Quack<>(Integer.class, 10);

    quack.push(1);
    quack.push(2);
    quack.push(3);
    int popped = quack.pop();
    assertEquals(popped, 3);
    int dq = quack.dequeue();
    assertEquals(dq, 1);
    assertEquals(quack.currentSize(), 1);
    assertEquals(quack.toString(), "[2]");

    quack.enqueue(4);
    quack.enqueue(5);
    assertEquals(quack.toString(), "[2 4 5]");
  }

  @Test
  public void multipleChunks() {
    Quack<Integer> quack = new Quack<>(Integer.class, 10);

    for (int i = 1; i <= 30; i++) {
      quack.push(i);
      System.out.println(quack);
      assertEquals(quack.currentSize(), i);
    }
    assertEquals(quack.toString(), "[1 2 3 4 5 6 7 8 9 10] [11 12 13 14 15 16 17 18 19 20] [21 22 23 24 25 26 27 28 29 30]");

    for (int i = 30; i >= 22; i--) {
      int popped = quack.pop();
      assertEquals(popped, i);
      System.out.println(quack);
    }
    assertEquals(quack.toString(), "[1 2 3 4 5 6 7 8 9 10] [11 12 13 14 15 16 17 18 19 20] [21]");
    assertEquals(quack.currentSize(), 21);

    for (int i = 1; i <= 9; i++) {
      int dq = quack.dequeue();
      assertEquals(dq, i);
      System.out.println(quack);
    }
    assertEquals(quack.toString(), "[10] [11 12 13 14 15 16 17 18 19 20] [21]");
    assertEquals(quack.currentSize(), 12);

    int dq = quack.dequeue();
    assertEquals(dq, 10);
    System.out.println(quack);
    assertEquals(quack.toString(), "[11 12 13 14 15 16 17 18 19 20] [21]");
    assertEquals(quack.currentSize(), 11);

    int popped = quack.pop();
    assertEquals(popped, 21);
    System.out.println(quack);
    assertEquals(quack.toString(), "[11 12 13 14 15 16 17 18 19 20]");
    assertEquals(quack.currentSize(), 10);
  }

  @Test (expected= IllegalStateException.class)
  public void emptyQuackPop() {
    Quack<String> quack = new Quack<>(String.class, 10);
    System.out.println(quack);
    quack.pop();
  }

  @Test (expected= IllegalStateException.class)
  public void emptyQuackDequeue() {
    Quack<String> quack = new Quack<>(String.class, 10);
    System.out.println(quack);
    quack.dequeue();
  }
}
