package practices.huawei.matrix.infectionDays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 计算疫情扩散天数
 *
 * @since 2025/7/12
 */
public class InfectionDays {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 1,0,1,0,0,0,1,0,1
    String inputStr = scanner.nextLine();
    // 将输入字符串转换为一维数组
    List<Integer> arr = convertToArray(inputStr);
    // 输出感染天数
    System.out.println(getInfectionDays(arr));
  }

  private static List<Integer> convertToArray(String inputStr) {
    return Arrays.stream(inputStr.split(",")).mapToInt(Integer::parseInt).boxed()
        .collect(Collectors.toList());
  }

  public static int getInfectionDays(List<Integer> arr) {
    int n = (int) Math.sqrt(arr.size());
    // 将一维数组转换为二维矩阵
    int[][] matrix = getMatrix(arr, n);
    // 用队列存储感染区域
    Queue<int[]> q = getAffectedQueue(n, matrix);
    // 记录未感染区域数量
    int healthy = getHealthy(n, matrix);

    return doCompute(n, matrix, q, healthy);
  }

  private static int doCompute(int n, int[][] matrix, Queue<int[]> affectedArea, int healthy) {
    // 判断特殊情况
    if (healthy == 0 || healthy == n * n) {
      return -1;
    }
    // 记录四个方向的偏移量
    int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 记录感染天数
    int day = 0;
    // 当队列不为空且还有未感染区域时，进行循环
    while (!affectedArea.isEmpty() && healthy > 0) {
      int[] tmp = affectedArea.poll(); // 取出队首元素
      int x = tmp[0], y = tmp[1]; // 获取队首元素的坐标
      day = matrix[x][y] + 1; // 记录感染天数

      for (int[] offset : offsets) { // 遍历四个方向
        int newX = x + offset[0]; // 新的横坐标
        int newY = y + offset[1]; // 新的纵坐标

        if (newX >= 0 && newX < n && newY >= 0 && newY < n
            && matrix[newX][newY] == 0) { // 判断边界和未感染区域
          healthy--; // 未感染区域数量减一
          matrix[newX][newY] = day; // 标记该区域已感染
          affectedArea.offer(new int[]{newX, newY}); // 将该区域加入队列
        }
      }
    }
    return day - 1;
  }

  private static Queue<int[]> getAffectedQueue(int n, int[][] matrix) {
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < n * n; i++) {
      int x = i / n;
      int y = i % n;
      if (matrix[x][y] == 1) {
        q.offer(new int[]{x, y}); // 将感染区域加入队列
      }
    }
    return q;
  }

  private static int getHealthy(int n, int[][] matrix) {
    int healthy = 0;
    for (int i = 0; i < n * n; i++) {
      int x = i / n;
      int y = i % n;
      if (matrix[x][y] != 1) {
        healthy++; // 计算未感染区域数量
      }
    }
    return healthy;
  }

  private static int[][] getMatrix(List<Integer> map, int n) {
    int[][] matrix = new int[n][n];
    for (int i = 0; i < n * n; i++) {
      int x = i / n;
      int y = i % n;
      matrix[x][y] = map.get(i); // 将一维数组转换为二维矩阵
    }
    return matrix;
  }
}
