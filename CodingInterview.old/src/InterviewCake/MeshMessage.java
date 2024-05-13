package InterviewCake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

// https://www.interviewcake.com/question/java/mesh-message
public class MeshMessage {
  private Map<String, String[]> graph;
  private ArrayList<String> pathSoFar;
  private HashSet<String> visitedSet;
  private boolean reachedEndNode;

  public MeshMessage() {
    graph = new HashMap<>() {
      {
        put("a", new String[]{"b", "c", "d"});
        put("b", new String[]{"a", "d"});
        put("c", new String[]{"a", "e"});
        put("d", new String[]{"a", "b"});
        put("e", new String[]{"c"});
        put("f", new String[]{"g"});
        put("g", new String[]{"f"});
      }
    };
  }

  public void setGraph(Map<String, String[]> graph) {
    this.graph = graph;
  }

  public String[] getPath(String startNode, String endNode) {
    if (!graph.containsKey(startNode)) {
      throw new IllegalStateException(String.format("Starting node %s not present", startNode));
    }

    if (!graph.containsKey(endNode)) {
      throw new IllegalStateException(String.format("Ending node %s not present", endNode));
    }

    visitedSet = new HashSet<>();
    pathSoFar = new ArrayList<>(){};
    pathSoFar.add(startNode);
    reachedEndNode = false;

    if (bfs(startNode, endNode)) {
      String[] returnArray = new String[pathSoFar.size()];
      return pathSoFar.toArray(returnArray);
    } else {
      return null;
    }
  }

  // Execute breadth first search, updating pathSoFar as we go
  private boolean bfs(String startNode, String endNode) {
    visitedSet.add(startNode);

    if (startNode.equals(endNode)) {
      reachedEndNode = true;
      return true;
    }

    for (String connection : graph.get(startNode)) {
      if (!visitedSet.contains(connection)) {
        pathSoFar.add(connection);
        if (!bfs(connection, endNode)) {
          pathSoFar.remove(connection);
        }
      }
    }

    return reachedEndNode;
  }
  private static Map<String, String[]> getNetwork() {
    return new HashMap<String, String[]>() {
      {
        put("a", new String[]{"b", "c", "d"});
        put("b", new String[]{"a", "d"});
        put("c", new String[]{"a", "e"});
        put("d", new String[]{"a", "b"});
        put("e", new String[]{"c"});
        put("f", new String[]{"g"});
        put("g", new String[]{"f"});
      }
    };
  }






  // tests

  @Test
  public void twoHopPath1Test() {
    final String[] expected = {"a", "c", "e"};
    final String[] actual = new MeshMessage().getPath("a", "e");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void twoHopPath2Test() {
    final String[] expected = {"d", "a", "c"};
    final String[] actual = new MeshMessage().getPath("d", "c");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void oneHopPath1Test() {
    final String[] expected = {"a", "c"};
    final String[] actual = new MeshMessage().getPath("a", "c");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void oneHopPath2Test() {
    final String[] expected = {"f", "g"};
    final String[] actual = new MeshMessage().getPath("f", "g");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void oneHopPath3Test() {
    final String[] expected = {"g", "f"};
    final String[] actual = new MeshMessage().getPath("g", "f");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void zeroHopPath() {
    final String[] expected = {"a"};
    final String[] actual = new MeshMessage().getPath("a", "a");
    assertNotNull(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void noPathTest() {
    final String[] actual = new MeshMessage().getPath("a", "f");
    assertNull(actual);
  }

  @Test(expected = Exception.class)
  public void startNodeNotPresentTest() {
    new MeshMessage().getPath("h", "a");
  }

  @Test(expected = Exception.class)
  public void endNodeNotPresentTest() {
    new MeshMessage().getPath("a", "h");
  }

}
