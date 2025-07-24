package practices.huawei.other.poke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TODO
 *
 * @since 2025/7/16
 */
public class LongestChain {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] my = sc.nextLine().split("-");
    String[] used = sc.nextLine().split("-");

    System.out.println(getResult(my, used));
  }

  public static String getResult(String[] my, String[] used) {
    // 牌面值 映射为 count列表索引值
    HashMap<String, Integer> mapToV = new HashMap<>();
    mapToV.put("3", 3);
    mapToV.put("4", 4);
    mapToV.put("5", 5);
    mapToV.put("6", 6);
    mapToV.put("7", 7);
    mapToV.put("8", 8);
    mapToV.put("9", 9);
    mapToV.put("10", 10);
    mapToV.put("J", 11);
    mapToV.put("Q", 12);
    mapToV.put("K", 13);
    mapToV.put("A", 14);
    mapToV.put("2", 16);
    mapToV.put("B", 17);
    mapToV.put("C", 18);

    // count每个索引值对应一个牌面值，count元素值就是对应牌面的数量
    // 牌面值             3  4  5  6  7  8  9  10 J  Q  K  A     2  B  C
    // 索引值             3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18
    int[] count = {0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 1, 1};

    // count列表索引值 隐射为 牌面值
    HashMap<Integer, String> mapToK = new HashMap<>();
    mapToK.put(3, "3");
    mapToK.put(4, "4");
    mapToK.put(5, "5");
    mapToK.put(6, "6");
    mapToK.put(7, "7");
    mapToK.put(8, "8");
    mapToK.put(9, "9");
    mapToK.put(10, "10");
    mapToK.put(11, "J");
    mapToK.put(12, "Q");
    mapToK.put(13, "K");
    mapToK.put(14, "A");
    mapToK.put(16, "2");
    mapToK.put(17, "B");
    mapToK.put(18, "C");

    // 总牌数 减去 自己手中牌数
    for (String k : my) {
      count[mapToV.get(k)] -= 1;
    }

    // 总牌数 减去 已打出去的牌数
    for (String k : used) {
      count[mapToV.get(k)] -= 1;
    }

    String ans = "NO-CHAIN";
    int maxLen = 0;

    // l为顺子的左边界，[3,10]，即顺子的左边界值最少是count索引3，最多是count索引10
    int left = 3;
    while (left <= 10) {
      ArrayList<String> tmp = new ArrayList<>();
      StringJoiner sj = new StringJoiner("-");
      for (int right = left; right < 16; right++) {
        // 如果对应牌数>=1，则可以组顺子
        if (count[right] >= 1) {
          tmp.add(mapToK.get(right));
          sj.add(mapToK.get(right));
        } else {
          // 如果对应牌数 == 0，则顺子中断
          // 顺子必须大于五张牌，且总是记录最长，遇到长度相同的，记录后面发现的顺子
          if (tmp.size() >= 5 && tmp.size() >= maxLen) {
            maxLen = tmp.size();
            ans = sj.toString();
          }
          // 顺子中断处+1，即为下一次顺子的起始位置
          left = right;
          break;
        }
      }
      left++;
    }

    return ans;
  }

}
