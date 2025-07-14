package practices.huawei.matrix;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/13
 */
public class RobotArea {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // 读取矩阵的行数和列数
    int m = sc.nextInt();
    int n = sc.nextInt();
    // 读取矩阵内容
    int[][] grid = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = sc.nextInt();
      }
    }
    // 输出最大区域的大小
    System.out.println(getResult(grid, m, n));
  }

  // 计算矩阵中最大的连通区域的大小
  public static int getResult(int[][] grid, int m, int n) {
    boolean[][] visited = new boolean[m][n]; // 访问标记数组
    int maxSize = 0;  // 记录最大的区域大小
    // 四个方向的偏移量，上下左右
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 遍历整个矩阵
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // 如果当前点没有被访问过，执行 BFS 计算该区域的大小
        if (!visited[i][j]) {
          int size = bfs(grid, visited, i, j, m, n, dirs);
          // 更新最大区域的大小
          maxSize = Math.max(maxSize, size);
        }
      }
    }
    return maxSize;  // 返回最大的区域大小
  }

  // 使用 BFS 查找从 (x, y) 出发的连通区域的大小
  private static int bfs(int[][] grid, boolean[][] visited, int x, int y, int m, int n,
      int[][] dirs) {
    Queue<int[]> queue = new LinkedList<>();  // BFS 使用的队列
    queue.offer(new int[]{x, y});  // 将起始点加入队列
    visited[x][y] = true;  // 标记起始点为已访问
    int size = 1;  // 当前区域的大小，从起始点开始
    // 当队列不为空时，继续遍历
    while (!queue.isEmpty()) {
      int[] point = queue.poll();  // 弹出队列中的一个元素
      // 遍历当前点的四个方向
      for (int[] dir : dirs) {
        int newX = point[0] + dir[0];  // 计算新点的横坐标
        int newY = point[1] + dir[1];  // 计算新点的纵坐标
        // 确保新点在矩阵范围内，并且未被访问过
        // 同时新点与当前点的差值小于等于 1，才将新点加入队列
        if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]
            && Math.abs(grid[point[0]][point[1]] - grid[newX][newY]) <= 1) {
          visited[newX][newY] = true;  // 标记新点为已访问
          queue.offer(new int[]{newX, newY});  // 将新点加入队列
          size++;  // 增加当前区域的大小
        }
      }
    }
    return size;  // 返回当前区域的大小
  }
}
