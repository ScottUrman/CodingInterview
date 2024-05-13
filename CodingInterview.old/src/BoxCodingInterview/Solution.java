package BoxCodingInterview;

import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

  private final int k;
  private final PriorityQueue<Integer> queue;

  public Solution(int k) {
    this.k = k;
    queue = new PriorityQueue<>();
  }

  /**
   * Reads each line in the input file, and adds to the queue as needed so that we maintain the
   * kth largest integer found so far at any point
   */
  public void processFile(Path filePath) throws IOException {

    try (Scanner scanner = new Scanner(Files.newInputStream(filePath))) {
      while (scanner.hasNext()) {
        int candidate = Integer.parseInt(scanner.nextLine());
        if (queue.size() < k) {
          queue.add(candidate);
        } else if (candidate > queue.peek()) {
          queue.poll();
          queue.add(candidate);
        }
      }
    }
  }


  public void processDirectory(Path directory) throws IOException {
    System.out.println("Processing directory " + directory.toString());

    try (Stream<Path> stream = Files.list(directory)) {
      stream.forEach(file -> {
        try {
          if (Files.isDirectory(file.toAbsolutePath())) {
            processDirectory(file.toAbsolutePath());
          } else {
            System.out.println("Processing file " + file.toAbsolutePath());
            processFile(file.toAbsolutePath());
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  public Integer[] getTopKLargestIntegers(Path rootDir) throws IOException {
    processDirectory(rootDir);
    return queue.toArray(new Integer[0]);
  }


  /**
   * Helper method to write the specified string to the specified file
   */
  public static void writeToFile(File filePath, String contents) throws IOException {
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write(contents);
    }
  }

  static public void main(String[] args) throws IOException {
    Solution solution = new Solution(5);

    // Setup test environment
    TemporaryFolder tf = new TemporaryFolder();
    try {
      tf.create();
      writeToFile(tf.newFile("file1"), """
          9999
          8888
          """);

      writeToFile(tf.newFile("file2"), """
          5555
          7777
          7777
          """);

      File subdir1 = tf.newFolder("subdir1");
      writeToFile(new File(subdir1, "file3"), """
          8888
          1111
          """);

      File subdir2 = tf.newFolder("subdir2");
      writeToFile(new File(subdir2, "file4"), """
          2222
          3333
          1234567
          """);

      File subdir3 = tf.newFolder("subdir2/subdir3");
      writeToFile(new File(subdir3, "file5"), """
          222
          111
          9999
          0
          """);

      System.out.println(Arrays.toString(solution.getTopKLargestIntegers(tf.getRoot().toPath())));
    } finally {
      tf.delete();
    }
  }
}
