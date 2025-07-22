package practices.huawei.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 书籍叠放,给定一个二维整数数组
 * books，如果书A的长宽度都比B长宽大时，则允许将B排列放在A上面。现在有一组规格的书籍，书籍叠放时要求书籍不能做旋转，请计算最多能有多少个规格书籍能叠放在一起。
 * <p>
 * https://leetcode.cn/problems/russian-doll-envelopes/description/
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 *
 * @since 2025/7/22
 */
public class BookSuperpose {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // [[20,16],[15,11],[10,10],[9,10]]
    // [[5,4],[6,2],[6,7],[20,16],[15,11],[10,8],[9,10]]
    String input = sc.nextLine();

    // (?<=]),(?=\[) 正则表达式含义是：找这样一个逗号，前面跟着]，后面跟着[
    // 其中(?<=) 表示前面跟着
    // 其中(?=) 表示后面跟着
    Integer[][] books = getBooks(input);

    System.out.println(getResult(books));
  }

  private static Integer[][] getBooks(String input) {
    String substring = input.substring(1, input.length() - 1);
    Integer[][] books = Arrays.stream(substring.split("(?<=]),(?=\\[)")).map(
        s -> Arrays.stream(s.substring(1, s.length() - 1).split(",")).map(Integer::parseInt)
            .toArray(Integer[]::new)).toArray(Integer[][]::new);
    return books;
  }

  public static int getResult(Integer[][] books) {
    // 长度升序，若长度相同，则宽度降序
    Arrays.sort(books, (a, b) -> {
      if (a[0] != b[0]) {
        return a[0] - b[0];
      } else {
        return b[1] - a[1];
      }
    });
    System.out.println(Arrays.deepToString(books));
    Integer[] widths = Arrays.stream(books).map(book -> book[1]).toArray(Integer[]::new);
    System.out.println(Arrays.toString(widths));
    return getMaxLongestIncreaseSub(widths);
  }

  // 最长递增子序列
  public static int getMaxLongestIncreaseSub(Integer[] nums) {
    //  dp数组元素dp[i]含义是：长度为i+1的最优子序列的末尾数字
    // [1,3,4]表示只选一个，最小可以选到1的，选2个最大宽度最小是3，选3个最大宽度最小是4
    ArrayList<Integer> dp = new ArrayList<>();
    dp.add(nums[0]);

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > dp.get(dp.size() - 1)) {
        dp.add(nums[i]);
      } else {
        int idx = binarySearch(dp, nums[i]);
        dp.set(idx, nums[i]);
      }
    }

    System.out.println(dp);
    return dp.size();
  }

  public static int binarySearch(List<Integer> f, int target) {
    int low = 0, high = f.size() - 1;
    while (low < high) {
      int mid = (high - low) / 2 + low;
      if (f.get(mid) < target) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;
  }

}
