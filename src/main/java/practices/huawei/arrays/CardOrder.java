package practices.huawei.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @since 2025/7/12
 */
public class CardOrder {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Integer> numbers = new ArrayList<>();
    // 读取输入直到输入结束
    while (sc.hasNextInt()) {
      numbers.add(sc.nextInt());
    }
    // 输出处理后的卡片排列顺序
    System.out.println(processCards(numbers));
  }

  // 处理卡片排列的函数
  public static String processCards(List<Integer> numbers) {
    // Step 1: 统计每张卡片的出现次数
    Map<Integer, Integer> count = new HashMap<>();
    for (int num : numbers) {
      count.put(num, count.getOrDefault(num, 0) + 1);
    }
    // Step 2: 将卡片按出现次数分组
    Set<Integer> singles = new HashSet<>();
    Set<Integer> pairs = new HashSet<>();
    Set<Integer> triples = new HashSet<>();
    Set<Integer> quads = new HashSet<>();
    // 遍历出现次数统计，分配到相应的集合中
    for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
      int num = entry.getKey();
      int freq = entry.getValue();
      if (freq == 1) {
        singles.add(num);  // 1次出现的卡片
      } else if (freq == 2) {
        pairs.add(num);    // 2次出现的卡片
      } else if (freq == 3) {
        triples.add(num);  // 3次出现的卡片
      } else {
        quads.add(num);    // 4次出现的卡片
      }
    }
    // Step 3: 创建一个结果列表，存放排序后的卡片
    List<Integer> result = new ArrayList<>();
    // 处理出现次数为4的卡片，按出现次数和卡片值降序排列
    List<Integer> quadsList = new ArrayList<>(quads);
    quadsList.sort((a, b) -> count.get(b) != count.get(a) ? count.get(b) - count.get(a) : b - a);
    for (int q : quadsList) {
      for (int i = 0; i < count.get(q); i++) {
        result.add(q);  // 将出现次数为4的卡片添加到结果中
      }
    }
    // Step 4: 处理出现次数为3的卡片，并尽量与2次的卡片配对
    List<Integer> triplesList = new ArrayList<>(triples);
    List<Integer> pairsList = new ArrayList<>(pairs);
    Collections.sort(triplesList);  // 对出现次数为3的卡片升序排序
    Collections.sort(pairsList);    // 对出现次数为2的卡片升序排序
    while (!triplesList.isEmpty()) {
      int t = triplesList.remove(triplesList.size() - 1);  // 从列表末尾取出3次的卡片
      result.add(t);  // 将3次的卡片添加到结果中
      result.add(t);  // 添加两次
      result.add(t);  // 添加三次
      // 判断是否能和二次的卡片配对，或者如果没有二次卡片则与单次卡片配对
      if (!triplesList.isEmpty() && (pairsList.isEmpty()
          || triplesList.get(triplesList.size() - 1) > pairsList.get(pairsList.size() - 1))) {
        int leftover = triplesList.remove(triplesList.size() - 1);  // 从3次卡片中移除一个
        singles.add(leftover);  // 将剩余卡片的一个值放入单张卡片的集合中
        result.add(leftover);   // 添加剩余的单张卡片
        result.add(leftover);   // 再添加一次
      } else if (!pairsList.isEmpty()) {
        int p = pairsList.remove(pairsList.size() - 1);  // 从二次卡片中取出一个卡片
        result.add(p);  // 添加到结果中
        result.add(p);  // 再添加一次
      }
    }
    // Step 5: 处理出现次数为2的卡片
    while (!pairsList.isEmpty()) {
      int p = pairsList.remove(pairsList.size() - 1);  // 从二次卡片中取出一个
      result.add(p);  // 添加到结果中
      result.add(p);  // 再添加一次
    }
    // Step 6: 处理出现次数为1的卡片，按降序排列
    List<Integer> singlesList = new ArrayList<>(singles);
    singlesList.sort(Collections.reverseOrder());  // 将单张卡片按降序排列
    result.addAll(singlesList);  // 添加到结果中
    // 返回处理后的结果，使用空格连接
    return result.stream().map(String::valueOf).collect(Collectors.joining(" "));
  }
}
