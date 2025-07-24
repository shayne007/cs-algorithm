package practices.huawei.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 无向图染色
 * <p>
 * 给一个无向图染色，可以填红黑两种颜色，必须保证相邻两个节点不能同时为红色，输出有多少种不同的染色方案？
 *
 * @since 2025/7/21
 */
public class RedBlackColor {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // m为节点数量，n为边数量
    int m = sc.nextInt();
    int n = sc.nextInt();

    // 保存边信息
    int[][] edges = new int[n][2];
    for (int i = 0; i < n; i++) {
      edges[i][0] = sc.nextInt();
      edges[i][1] = sc.nextInt();
    }

    System.out.println(countSolutions(edges, m));
  }

  /**
   * @param edges 边，即[m1, m2]
   * @param m     节点数量
   * @return 染色方案数量
   */
  public static int countSolutions(int[][] edges, int m) {
    // connect用于存放每个节点的相邻节点的set集合
    Map<Integer, Set<Integer>> connect = getConnectMapping(edges);
    // 节点从index=1开始，必有count=1个的全黑染色方案
    return dfs(connect, m, 1, 1, new LinkedList<>());
  }

  private static Map<Integer, Set<Integer>> getConnectMapping(int[][] edges) {
    Map<Integer, Set<Integer>> connect = new HashMap<>();

    for (int[] edge : edges) {
      int m1 = edge[0];
      int m2 = edge[1];
      // 无向图，m1和m2互为邻居
      // 处理m1的邻居
      connect.putIfAbsent(m1, new HashSet<>());
      connect.get(m1).add(m2);
      // 处理m2的邻居
      connect.putIfAbsent(m2, new HashSet<>());
      connect.get(m2).add(m1);
    }
    return connect;
  }

  /**
   * 该方法用于求解给定多个节点染红的全组合数，深度优先搜索，递归实现 （回溯）
   *
   * @param nodeNeighborsMapping 节点邻居映射表
   * @param pointTotal           节点总数
   * @param index                当前节点编号
   * @param count                染色方案计数
   * @param path                 记录当前染色方案，使用LinkedList方便回溯
   * @return 染色方案总数
   */
  public static int dfs(Map<Integer, Set<Integer>> nodeNeighborsMapping, int pointTotal, int index,
      int count, LinkedList<Set<Integer>> path) {
    if (path.size() == pointTotal) {
      return count;
    }

    // 从 index=1 第一个节点开始，参与染色为Red
    outer:
    for (int i = index; i <= pointTotal; i++) {
      // 如果新加入节点i和已有某个节点相邻，则说明新加入节点不能染成红色，需要进行剪枝
      for (Set<Integer> node : path) {
        if (node.contains(i)) {
          continue outer;
        }
      }

      count++; // 节点i染红，count加1

      if (nodeNeighborsMapping.containsKey(i)) {
        path.addLast(nodeNeighborsMapping.get(i));
        count = dfs(nodeNeighborsMapping, pointTotal, i + 1, count, path);
        path.removeLast(); // 回溯
      } else {
        // 某些节点可能没有相邻节点
        count = dfs(nodeNeighborsMapping, pointTotal, i + 1, count, path);
      }
    }
    return count;
  }
}
