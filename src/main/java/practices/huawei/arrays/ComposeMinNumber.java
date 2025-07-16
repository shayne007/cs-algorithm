package practices.huawei.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数组中三个数字组成的最小数
 * <p>
 * [21,30,62,5,31]
 * <p>
 * 21305
 *
 * @since 2025/7/12
 */
public class ComposeMinNumber {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] numbers = sc.nextLine().split(","); // 读取输入并按逗号分割
    System.out.println(findMinComposedResult(numbers)); // 计算结果并输出
  }

  public static String findMinComposedResult(String[] numbers) {
    // 先按照数值大小排序，并取最小的 3 个元素
    Stream<String> three = Arrays.stream(numbers)
        .sorted(Comparator.comparingInt(Integer::parseInt))
        .limit(3);
    // 按两两拼接后的结果排序
    List<String> sortedList =
        three.sorted((a, b) -> (a + b).compareTo(b + a))
            .collect(Collectors.toList());
    // 拼接排序后的字符串列表并返回
    return String.join("", sortedList);
  }
}
