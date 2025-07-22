package practices.huawei.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 给一个无向图染色，可以填红黑两种颜色，必须保证相邻两个节点不能同时为红色，输出有多少种不同的染色方案？
 *
 * @since 2025/7/21
 */
public class RedBlackColor {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int m = sc.nextInt();
    int n = sc.nextInt();

    int[][] edges = new int[n][2];
    for (int i = 0; i < n; i++) {
      edges[i][0] = sc.nextInt();
      edges[i][1] = sc.nextInt();
    }

    System.out.println(getResult(edges, m));
  }

  /**
   * @param edges 边，即[v1, v2]
   * @param m     点数量
   * @return
   */
  public static int getResult(int[][] edges, int m) {
    // connect用于存放每个节点的相邻节点
    Map<Integer, Set<Integer>> connect = new HashMap<>();

    for (int[] edge : edges) {
      connect.putIfAbsent(edge[0], new HashSet<>());
      connect.get(edge[0]).add(edge[1]);

      connect.putIfAbsent(edge[1], new HashSet<>());
      connect.get(edge[1]).add(edge[0]);
    }

    // 节点从index=1开始，必有count=1个的全黑染色方案
    return dfs(connect, m, 1, 1, new LinkedList<>());
  }

  // 该方法用于求解给定多个节点染红的全组合数
  public static int dfs(Map<Integer, Set<Integer>> pointNeighborsMapping, int pointTotal, int index,
      int count,
      LinkedList<Set<Integer>> path) {
    if (path.size() == pointTotal) {
      return count;
    }

    // 从index=1第一个节点开始，参与染色为Red
    outer:
    for (int i = index; i <= pointTotal; i++) {
      // 如果新加入节点i和已有节点j相邻，则说明新加入节点不能染成红色，需要进行剪枝
      for (Set<Integer> p : path) {
        if (p.contains(i)) {
          continue outer;
        }
      }

      count++;

      if (pointNeighborsMapping.containsKey(i)) {
        path.addLast(pointNeighborsMapping.get(i));
        count = dfs(pointNeighborsMapping, pointTotal, i + 1, count, path);
        path.removeLast();
      } else {
        // 某些节点可能没有相邻节点
        count = dfs(pointNeighborsMapping, pointTotal, i + 1, count, path);
      }
    }
    return count;
  }
}
