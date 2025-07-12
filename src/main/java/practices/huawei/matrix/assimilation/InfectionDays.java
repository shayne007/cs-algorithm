package practices.huawei.matrix.assimilation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 感染天数计算
 *
 * @since 2025/7/12
 */
public class InfectionDays {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    List<Integer> map = new ArrayList<>();
    int pos = 0;
    String token;
    while ((pos = input.indexOf(",")) != -1) { // 将输入字符串转换为一维数组
      token = input.substring(0, pos);
      map.add(Integer.parseInt(token));
      input = input.substring(pos + 1);
    }
    map.add(Integer.parseInt(input));
    System.out.println(getInfectionDays(map)); // 输出感染天数
  }

  public static int getInfectionDays(List<Integer> map) {
    int n = (int) Math.sqrt(map.size());

    int[][] matrix = new int[n][n]; // 将一维数组转换为二维矩阵

    Queue<int[]> q = new LinkedList<>(); // 用队列存储感染区域

    int healthy = 0; // 记录未感染区域数量

    int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 记录四个方向的偏移量

    for (int i = 0; i < n * n; i++) {
      int x = i / n;
      int y = i % n;
      matrix[x][y] = map.get(i); // 将一维数组转换为二维矩阵
      if (matrix[x][y] == 1) {
        q.offer(new int[]{x, y}); // 将感染区域加入队列
      } else {
        healthy++; // 计算未感染区域数量
      }
    }

    if (healthy == 0 || healthy == n * n) { // 判断特殊情况
      return -1;
    }

    int day = 0; // 记录感染天数
    while (!q.isEmpty() && healthy > 0) { // 当队列不为空且还有未感染区域时，进行循环
      int[] tmp = q.poll(); // 取出队首元素
      int x = tmp[0], y = tmp[1]; // 获取队首元素的坐标
      day = matrix[x][y] + 1; // 记录感染天数

      for (int[] offset : offsets) { // 遍历四个方向
        int newX = x + offset[0]; // 新的横坐标
        int newY = y + offset[1]; // 新的纵坐标

        if (newX >= 0 && newX < n && newY >= 0 && newY < n
            && matrix[newX][newY] == 0) { // 判断边界和未感染区域
          healthy--; // 未感染区域数量减一
          matrix[newX][newY] = day; // 标记该区域已感染
          q.offer(new int[]{newX, newY}); // 将该区域加入队列
        }
      }
    }

    return day - 1; // 返回感染天数
  }
}
