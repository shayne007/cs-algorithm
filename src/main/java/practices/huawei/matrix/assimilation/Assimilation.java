package practices.huawei.matrix.assimilation;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 免疫同化计数
 *
 * @since 2025/7/12
 */
public class Assimilation {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int m = sc.nextInt();
    int n = sc.nextInt();

    int[][] matrix = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = sc.nextInt();
      }
    }

    matrix[0][0] = 1;

    System.out.println(countUnassimilated(m, n, matrix));
  }

  /**
   * 统计矩阵中值为0或2的元素个数
   *
   * @param m      rows
   * @param n      columns
   * @param matrix matrix
   * @return count
   */
  public static int countUnassimilated(int m, int n, int[][] matrix) {
    // 上、下、左、右偏移量
    int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 广搜队列
    LinkedList<int[]> queue = new LinkedList<>();

    // 初始时只有矩阵[0,0]位置元素为1
    queue.add(new int[]{0, 0});

    // count记录矩阵中值为1的元素的个数
    int count = 1;

    // 广搜
    while (queue.size() > 0) {
      int[] pos = queue.removeFirst();

      int x = pos[0];
      int y = pos[1];

      for (int[] offset : offsets) {
        int newX = x + offset[0];
        int newY = y + offset[1];

        if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == 0) {
          matrix[newX][newY] = 1;
          count++;
          queue.add(new int[]{newX, newY});
        }
      }
    }

    return m * n - count;
  }
}
