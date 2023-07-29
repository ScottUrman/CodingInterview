package InterviewingIO;

import java.lang.reflect.Array;

import static com.google.common.base.Preconditions.checkState;

/*
Implement a combined queue and stack, known as a quack, with the following API:

  Quack(int initialSize): initializes a quack with the requested initial number of elements
  push(T newElement): push a new element on the top of the quack
  T pop(): pop the topmost element from the quack
  enqueue (T newElement): Add a new element to the end of the quack
  T dequeue(): Remove the earliest element from the quack
  int currentSize(): Return the current number of elements in the quack

  Example:
  Quack quack = new Quack(10);
  quack.push('A');
  quack.push('B');
  quack.push('C');
  quack.currentSize() -> 3
  quack.pop() -> 'C'
  quack.enqueue('D')
  quack.pop() -> 'D'
  quack.dequeue() -> 'A'
  quack.dequeue() -> 'B'
  quack.currentSize() -> 0

  Stick to only basic arrays please, i.e. don't use a resizable array such as a Java ArrayList.
 */
public class Quack <T> {
  int chunkSize;
  Class<T> elementType;
  Chunk topChunk = null;
  Chunk bottomChunk = null;
  int numElements = 0;
  class Chunk {
    T[] elements = (T[]) Array.newInstance(elementType, chunkSize);
    int top = -1;
    int bottom = -1;
    Chunk next = null;
    Chunk previous = null;

    Chunk() {
    }

    void push(T newElement) {
      // A chunk is full if top == chunkSize - 1
      checkState(top < chunkSize -1, "Chunk is full");
      elements[++top] = newElement;
      if (bottom == -1) {
        bottom = 0;
      }
    }

    void enqueue(T newElement) {
      push(newElement);
    }

    T pop() {
      // A chunk is empty if top == bottom == -1
      checkState(top != -1 && bottom != -1, "Chunk is empty");
      T retVal = elements[top--];
      if (top < bottom) {
        // chunk is now empty, reset
        top = bottom = -1;
      }
      return retVal;
    }

    T dequeue() {
      checkState(top != -1 && bottom != -1, "Chunk is empty");
      T retVal = elements[bottom++];
      if (top < bottom) {
        // chunk is now empty, reset
        top = bottom = -1;
      }
      return retVal;
    }

    boolean isEmpty() {
      return (top == -1) && (bottom == -1);
    }

    boolean isFull() {
      return (top == chunkSize - 1);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();

      for (int i = bottom; i <= top; i++) {
        if (i == bottom) {
          sb.append("[");
        } else {
          sb.append(" ");
        }
        if (i != -1) {
          sb.append(elements[i]);
        }
      }
      sb.append("]");
      return sb.toString();
    }
  }

  Quack(Class<T> clazz, int chunkSize) {
    this.chunkSize = chunkSize;
    this.elementType = clazz;
  }

  public int currentSize() {
    return numElements;
  }

  public void push(T newElement) {
    if (topChunk == null) {
      topChunk = bottomChunk = new Chunk();
    } else if (topChunk.isFull()) {
      Chunk newChunk = new Chunk();
      topChunk.next = newChunk;
      newChunk.previous = topChunk;
      topChunk = newChunk;
    }

    topChunk.push(newElement);
    numElements++;
  }

  public T pop() {
    checkState(numElements > 0, "Quack is empty");
    numElements--;

    T retVal = topChunk.pop();
    if (topChunk.isEmpty()) {
      topChunk = topChunk.previous;
      if (topChunk != null) {
        topChunk.next = null;  // Garbage collect the original top chunk
      }
    }

    return retVal;
  }

  public void enqueue(T newElement) {
    push(newElement);
  }

  public T dequeue() {
    checkState(numElements > 0, "Quack is empty");
    numElements--;

    T retVal = bottomChunk.dequeue();
    if (bottomChunk.isEmpty()) {
      bottomChunk = bottomChunk.next;
      if (bottomChunk != null) {
        bottomChunk.previous = null;  // Garbage collect the original bottom chunk
      }
    }
    return retVal;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (bottomChunk == null) {
      return "[]";
    }

    for (Chunk chunk = bottomChunk; chunk != null; chunk = chunk.next) {
      if (chunk != bottomChunk) {
        sb.append(" ");
      }
      sb.append(chunk);
    }
    return sb.toString();
  }
}
