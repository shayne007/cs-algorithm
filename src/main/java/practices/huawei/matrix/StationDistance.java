package practices.huawei.matrix;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 基站维护
 * <p>
 * 小王从基站 1 出发，途经每个基站 1 次，然后返回基站 1 ，需要请你为他选择一条距离最短的路。
 *
 * @since 2025/7/22
 */
public class StationDistance {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 3
    int n = sc.nextInt();

    // 每个基站之间的距离，基站 x 到基站 y 的距离，与基站 y 到基站 x 的距离并不一定会相同。
    // 0 2 1
    // 1 0 2
    // 2 1 0
    int[][] matrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = sc.nextInt();
      }
    }

    System.out.println(getResult(matrix, n));
  }

  public static int getResult(int[][] matrix, int n) {
    boolean[] used = new boolean[n];
    LinkedList<Integer> queue = new LinkedList<>();
    int[] ans = {Integer.MAX_VALUE};

    dfs(n, used, queue, ans, matrix);
    return ans[0];
  }

  /**
   * 深度优先搜索, 路径有1 2 3 1 与 1 2 3 1两条，即 2 和 3 的全排列
   * @param n
   * @param used
   * @param pathQueue
   * @param ans
   * @param matrix
   */
  public static void dfs(
      int n, boolean[] used, LinkedList<Integer> pathQueue, int[] ans, int[][] matrix) {
    // 已排列全部基站
    if (pathQueue.size() == n - 1) {
      int dis = matrix[0][pathQueue.get(0)];
      for (int i = 0; i < pathQueue.size() - 1; i++) {
        int p = pathQueue.get(i);
        int c = pathQueue.get(i + 1);
        dis += matrix[p][c];
      }
      dis += matrix[pathQueue.getLast()][0];
      ans[0] = Math.min(ans[0], dis);
      System.out.println(pathQueue);
      return;
    }

    for (int i = 1; i < n; i++) {
      if (!used[i]) {
        pathQueue.push(i);
        used[i] = true;
        dfs(n, used, pathQueue, ans, matrix);
        used[i] = false;
        // 回溯
        pathQueue.pop();
      }
    }
  }
}
