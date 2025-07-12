package practices.huawei.arrays;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 高矮person line
 * <p>
 * 输入： 4 1 3 5 2
 * <p>
 * 输出： 4 1 5 2 3
 *
 * @since 2025/7/12
 */
public class PersonLine {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();

    // 使用正则表达式检查输入字符串是否只包含数字和空格
    // 如果字符串中包含非法字符（非数字或空格），则输出"[]"并退出程序
    if (!s.matches("[0-9\\s]+")) {
      System.out.println("[]");
      return;
    }

    // 将输入字符串按空格分割，并将每个部分转换为整数，存储在数组heights中
    int[] heights = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();

    // 初始化两个指针i和j，分别指向相邻的两个小朋友
    int i = 0, j = 1;

    // 遍历数组，调整相邻小朋友的身高顺序以满足"高矮交替"的要求
    while (j < heights.length) {
      // 判断当前两个相邻小朋友的身高是否满足要求
      // 条件解释：如果heights[i] > heights[j]且i是偶数，或者heights[i] < heights[j]且i是奇数
      // 则需要交换heights[i]和heights[j]的值，以符合"高矮交替"的规则
      if (heights[i] != heights[j] && (heights[i] > heights[j]) != (i % 2 == 0)) {
        // 交换heights[i]和heights[j]的值
        int tmp = heights[i];
        heights[i] = heights[j];
        heights[j] = tmp;
      }

      // 移动指针，检查下一个相邻的小朋友
      i++;
      j++;
    }

    // 使用StringJoiner将排序后的身高数组转换为字符串，并以空格分隔
    StringJoiner sj = new StringJoiner(" ");
    for (int h : heights) {  // 遍历heights数组中的每一个元素
      sj.add(String.valueOf(h));  // 将元素转换为字符串并添加到StringJoiner中
    }
    // 输出最终排序结果
    System.out.println(sj.toString());
  }

}
