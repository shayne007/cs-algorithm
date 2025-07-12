package practices.huawei.matrix;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 计算二维矩阵中连通块的个数
 *
 * @since 2025/7/12
 */
public class ConnectedBlocks {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // 输入矩阵的行数和列数
    int rowCount = input.nextInt();
    int colCount = input.nextInt();

    // 初始化矩阵
    int[][] matrix = new int[rowCount][colCount];
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < colCount; j++) {
        matrix[i][j] = input.nextInt();
      }
    }

    // 调用计算连通块数量的函数并输出结果
    System.out.println(calculateClicks(matrix, rowCount, colCount));
  }

  // 计算连通块数量的函数
  public static int calculateClicks(int[][] matrix, int rowCount, int colCount) {
    // 标记矩阵，用于记录某个位置是否已访问
    boolean[][] marked = new boolean[rowCount][colCount];
    // 初始化连通块计数器
    int clickCount = 0;

    // 遍历矩阵的每个元素
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < colCount; j++) {
        // 如果当前元素是未访问的'1'，视为新连通块
        if (matrix[i][j] == 1 && !marked[i][j]) {
          clickCount++;
          // 使用队列进行BFS
          Queue<int[]> queue = new LinkedList<>();
          queue.add(new int[]{i, j});
          marked[i][j] = true; // 标记为已访问

          // BFS遍历连通块中的所有元素
          while (!queue.isEmpty()) {
            int[] point = queue.poll(); // 取出队列头部的坐标
            int x = point[0];
            int y = point[1];

            // 遍历当前位置的八个方向
            for (int[] dir : new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},
                {1, 0}, {1, 1}}) {
              int newX = x + dir[0];
              int newY = y + dir[1];

              // 检查新坐标是否在矩阵范围内，且是未访问的'1'
              if (newX >= 0 && newX < rowCount && newY >= 0 && newY < colCount
                  && matrix[newX][newY] == 1 && !marked[newX][newY]) {
                marked[newX][newY] = true; // 标记新坐标为已访问
                queue.add(new int[]{newX, newY}); // 将新坐标加入队列
              }
            }
          }
        }
      }
    }

    // 返回连通块的数量
    return clickCount;
  }
}
