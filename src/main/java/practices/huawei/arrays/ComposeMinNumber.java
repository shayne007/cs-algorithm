package practices.huawei.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * s
 * [21,30,62,5,31]
 *
 *
 * @since 2025/7/12
 */
public class ComposeMinNumber {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] strs = sc.nextLine().split(","); // 读取输入并按逗号分割
    System.out.println(findResult(strs)); // 计算结果并输出
  }

  public static String findResult(String[] strs) {
    // 先按照数值大小排序，并取最小的 3 个元素
    List<String> sortedList = Arrays.stream(strs)
        .sorted(Comparator.comparingInt(Integer::parseInt)) // 数值排序
        .limit(3) // 取最小的 3 个元素
        .sorted((a, b) -> (a + b).compareTo(b + a)) // 按拼接结果排序
        .collect(Collectors.toList());
    // 拼接排序后的字符串列表并返回
    return String.join("", sortedList);
  }
}
