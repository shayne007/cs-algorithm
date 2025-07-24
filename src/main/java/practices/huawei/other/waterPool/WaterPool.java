package practices.huawei.other.waterPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/21
 */
public class WaterPool {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Integer[] h =
        Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

    System.out.println(getResult(h));
  }

  public static String getResult(Integer[] h) {
    int n = h.length;

    // left[i] 记录 第 i 个山峰左边的最高山峰
    int[] left = new int[n];
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], h[i - 1]);
    }

    // right[i] 记录 第 i 个山峰右边的最高山峰
    int[] right = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], h[i + 1]);
    }

    // lines[i] 记录 第 i 个山峰的水位线高度
    int[] lines = new int[n];
    // lineSet记录有哪些水位线
    HashSet<Integer> lineSet = new HashSet<>();
    for (int i = 1; i < n - 1; i++) {
      int water = Math.max(0, Math.min(left[i], right[i]) - h[i]); // water 记录 第 i 个山峰可以储存多少水

      if (water != 0) {
        // 第 i 个山峰的水位线高度
        lines[i] = water + h[i];
        lineSet.add(lines[i]);
      }
    }

    // ans数组含义：[左边界， 右边界， 储水量]
    int[] ans = {0, 0, 0};

    // 遍历每一个水位线
    for (int line : lineSet) {

      // 满足该水位线的最左侧山峰位置l
      int l = 0;
      while (lines[l] < line || h[l] >= line) {
        l++;
      }

      // 满足该水位线的最右侧山峰位置r
      int r = n - 1;
      while (lines[r] < line || h[r] >= line) {
        r--;
      }

      // 该水位线的总储水量
      int sum = 0;
      for (int i = l; i <= r; i++) {
        sum += Math.max(0, line - h[i]);
      }

      // 记录最大的储水量
      if (sum > ans[2]) {
        ans[0] = l - 1;
        ans[1] = r + 1;
        ans[2] = sum;
      }
      // 如果有多个最多储水量选择，则选择边界山峰距离最短的
      else if (sum == ans[2]) {
        int curDis = r - l + 1;
        int minDis = ans[1] - ans[0] - 1;

        if (curDis < minDis) {
          ans[0] = l - 1;
          ans[1] = r + 1;
        }
      }
    }

    if (ans[2] == 0) {
      return "0";
    }

    return ans[0] + " " + ans[1] + ":" + ans[2];
  }
}
