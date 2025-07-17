package practices.huawei.arrays.planting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 最佳植树距离
 * <p>
 * 输入：适合种树的坐标数量: 7 适合种树的坐标位置: 1 5 3 6 10 7 13 树苗的数量: 3
 * <p>
 * 输出：最佳的最小种植间距: 6
 * <p>
 * 3棵树苗分别种植在1，7，13位置时，树苗种植的最均匀，最小间距为6
 *
 * @link https://leetcode.cn/problems/magnetic-force-between-two-balls/description/
 * @since 2025/7/17
 */
public class MaxDistance {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // 输入：适合种树的坐标数量: 7
    int n = sc.nextInt();
    // 适合种树的坐标位置: 1 5 3 6 10 7 13
    int[] positions = new int[n];
    for (int i = 0; i < n; i++) {
      positions[i] = sc.nextInt();
    }
    // 树苗的数量: 3
    int m = sc.nextInt();

    System.out.println(getMaxDistance(n, positions, m));
  }

  public static int getMaxDistance(int size, int[] positions, int num) {
    Arrays.sort(positions);

    int minVal = 1, maxVal = positions[size - 1] - positions[0];
    int distance = 0;

    while (minVal <= maxVal) {
      // 中间值
      int mid = (minVal + maxVal) >> 1;
      // 检测是否能放下num棵树苗
      if (check(positions, num, mid)) {
        distance = mid;
        minVal = mid + 1;
      } else {
        maxVal = mid - 1;
      }
    }

    return distance;
  }

  /**
   * 植树距离为minDis时，检测是否可以放下num棵树苗
   *
   * @param positions 所有位置
   * @param num       树苗数量
   * @param minDis    最小距离
   * @return 是否可以放下num棵树苗
   */
  public static boolean check(int[] positions, int num, int minDis) {
    int count = 1;
    //第一棵树位置
    int curPos = positions[0];
    List<Integer> ps = new ArrayList<>();
    ps.add(curPos);
    // 迭代下一棵树的位置，累计个数
    for (int i = 1; i < positions.length; i++) {
      if (positions[i] - curPos >= minDis) {
        count++;
        curPos = positions[i];
        ps.add(curPos);
      }
    }
    if (count >= num) {
      System.out.println(ps);
    }
    return count >= num;
  }
}
