package practices.huawei.sevenGame;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 抢7游戏
 *
 * @since 2025/7/22
 */
public class SevenGame {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int firstNum = sc.nextInt();

    BigInteger[] factor = initFactor(firstNum - 7);

    int oneCount = firstNum - 7;
    int twoCount = 0;

    // 记录B赢的情况数
    BigInteger ans = new BigInteger("0");

    while (oneCount >= 0) {
      // 叫的次数为奇数时，才能B赢
      if ((oneCount + twoCount) % 2 != 0) {
        ans = ans.add(getPermutationCount(oneCount, twoCount, factor));
      }

      // 合并两个1为一个2
      oneCount -= 2;
      twoCount += 1;
    }

    System.out.println(ans);
  }

  // 求解不重复的全排列数
  public static BigInteger getPermutationCount(int oneCount, int twoCount, BigInteger[] factor) {
    if (oneCount == 0 || twoCount == 0) { // 即 1 1 1 1 1 或 2 2 2 这种情况，此时只有一种排列
      return new BigInteger("1");
    } else {
      // 排列数去重，比如 1 1 1 2 2 的不重复排列数为 5! / 3! / 2! = 10
      return factor[oneCount + twoCount].divide(factor[oneCount].multiply(factor[twoCount]));
    }
  }

  // 阶乘
  public static BigInteger[] initFactor(int n) {
    BigInteger[] factor = new BigInteger[n + 1];
    factor[0] = new BigInteger("1");
    for (int i = 1; i <= n; i++) {
      factor[i] = factor[i - 1].multiply(new BigInteger(i + ""));
    }
    return factor;
  }
}
