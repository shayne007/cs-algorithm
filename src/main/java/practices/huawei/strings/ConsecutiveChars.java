package practices.huawei.strings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 连续字符统计,单个字符返回其之后出现的次数
 * <p>
 * 连续字符最大统计 参考题：https://leetcode.cn/problems/consecutive-characters
 * <p>输入
 * bcaAAbB
 * <p>输出，第一b不连续，返回b2；c不连续返回后面有一个c，返回c1，以此类推得到：b2a3b2c0，数字排序后得到结果如下：
 * a3b2b2c0
 *
 * @since 2025/7/24
 */
public class ConsecutiveChars {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // bcaAAbB
    String input = in.nextLine();
    input = input.toLowerCase() + "@";
    int count = 1;
    List<String> chars = new ArrayList<>();
    for (int i = 1; i < input.length(); i++) {
      if (input.charAt(i) == input.charAt(i - 1)) {
        count++;
      } else {
        chars.add(count + String.valueOf(input.charAt(i - 1)));
        count = 1;
      }
    }
    chars.sort(Comparator.reverseOrder());

    System.out.println(chars.stream().collect(Collectors.joining()));
  }


}
