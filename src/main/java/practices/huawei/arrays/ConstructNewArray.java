package practices.huawei.arrays;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 组装新的数组
 * <p>
 * 输入	2 targetSum=5 只有1种组装办法，就是[2,2,1]
 * <p>
 * 输入	2 3 targetSum=5 一共两种组装办法，分别是[2,2,1]，[2,3]
 *
 * @since 2025/7/18
 */
public class ConstructNewArray {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // 2 3
    String[] strings = sc.nextLine().split(" ");
    Integer[] arr = Arrays.stream(strings).map(Integer::parseInt).toArray(Integer[]::new);
    // 5
    int targetSum = Integer.parseInt(sc.nextLine());

    System.out.println(getResult(arr, targetSum));
  }

  public static int getResult(Integer[] arr, int m) {
    Integer[] newArr = Arrays.stream(arr).filter(val -> val <= m).toArray(Integer[]::new);

    return dfs(newArr, 0, 0, arr[0], m, 0);
  }

  public static int dfs(Integer[] arr, int index, int sum, int min, int target, int count) {
    if (sum > target) {
      return count;
    }

    if (sum == target || (target - sum < min && target - sum > 0)) {
      return count + 1;
    }

    for (int i = index; i < arr.length; i++) {
      count = dfs(arr, i, sum + arr[i], min, target, count);
    }

    return count;
  }
}
