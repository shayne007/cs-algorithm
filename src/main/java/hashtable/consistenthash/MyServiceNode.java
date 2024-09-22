package hashtable.consistenthash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author fengsy
 * @date 7/8/20
 * @Description
 */
public class MyServiceNode implements Node {

  private final String idc;
  private final String ip;
  private final int port;

  public MyServiceNode(String idc, String ip, int port) {
    this.idc = idc;
    this.ip = ip;
    this.port = port;
  }

  @Override
  public String getKey() {
    return idc + "-" + ip + ":" + port;
  }

  @Override
  public String toString() {
    return getKey();
  }

  public static void main(String[] args) {
    List nodes = new ArrayList();
    Map<Node, Integer> hitMap = new HashMap<>();
    int nodeSize = 10;
    for (int i = 0; i < nodeSize; i++) {
      MyServiceNode node = new MyServiceNode("IDC" + i, "192.168.0." + i, 8080);
      nodes.add(node);
      hitMap.put(node, 0);
    }

    int requestSize = 1000000;
    String requestKey = "request-url-";

    List<String> requests = new LinkedList<>();
    for (int i = 0; i < requestSize; i++) {
      requests.add(requestKey + i);
    }

    for (int i = 1; i < 200; i = i + 1) {
      ConsistentHashRouter<MyServiceNode> consistentHashRouter = new ConsistentHashRouter<>(nodes,
          i);
      goRoute(consistentHashRouter, requests, hitMap);
      double std = calculateStd(nodeSize, requestSize, hitMap);
      System.out.println("vnodeSize:" + i + ",  std:" + std);
      for (Node node : hitMap.keySet()) {
//        System.out.println("node:::" + node.getKey() + ",hits:::" + hitMap.get(node));
        hitMap.put(node, 0);
      }
    }
  }

  private static Map<Node, Integer> goRoute(
      ConsistentHashRouter<MyServiceNode> consistentHashRouter,
      List<String> requestIps, Map<Node, Integer> hitMap) {

    for (String requestIp : requestIps) {
      Node node = consistentHashRouter.routeNode(requestIp);
//      System.out.println(requestIp + " is route to " + consistentHashRouter.routeNode(requestIp));
      hitMap.put(node, hitMap.get(node) + 1);
    }
    return hitMap;
  }

  private static int calculateStd(int nodeSize, int requestSize, Map<Node, Integer> hitMap) {

    int average = requestSize / nodeSize;
    double temp = hitMap.values().stream().map(value -> Math.pow((value - average), 2))
        .reduce(0.0, (s, e) -> s = s + e);
    return (int) Math.sqrt(temp / nodeSize);
  }

}
