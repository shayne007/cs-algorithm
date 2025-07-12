package practices.huawei.arrays.callLost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TODO
 *
 * @since 2025/7/12
 */
public class AverageLost {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 容忍的平均失败率
    int toleratedAverageLoss = Integer.parseInt(scanner.nextLine());

    // 读取失败率数组
    Integer[] failureRates =
        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

    System.out.println(getResult(toleratedAverageLoss, failureRates));
  }

  private static String getResult(int toleratedAverageLoss, Integer[] failureRates) {
    int arrayLength = failureRates.length;

    // 创建一个累积和数组，用于快速计算任意时间段的失败率总和
    int[] cumulativeSum = new int[arrayLength];
    cumulativeSum[0] = failureRates[0];
    for (int i = 1; i < arrayLength; i++) {
      cumulativeSum[i] = cumulativeSum[i - 1] + failureRates[i];
    }

    // 存储满足条件的时间段的开始和结束索引
    ArrayList<Integer[]> validPeriods = new ArrayList<>();
    int maxLength = 0;
    for (int start = 0; start < arrayLength; start++) {
      for (int end = start; end < arrayLength; end++) {
        int sum = start == 0 ? cumulativeSum[end] : cumulativeSum[end] - cumulativeSum[start - 1];
        int length = end - start + 1;
        int toleratedLoss = length * toleratedAverageLoss;

        // 如果这个时间段的平均失败率小于等于容忍的平均失败率
        if (sum <= toleratedLoss) {
          // 如果这个时间段比之前找到的时间段更长，清空结果列表并添加这个时间段
          if (length > maxLength) {
            validPeriods = new ArrayList<>();
            validPeriods.add(new Integer[]{start, end});
            maxLength = length;
          }
          // 如果这个时间段和之前找到的最长时间段一样长，添加这个时间段
          else if (length == maxLength) {
            validPeriods.add(new Integer[]{start, end});
          }
        }
      }
    }

    // 如果没有找到满足条件的时间段，输出"NULL"
    String aNull;
    if (validPeriods.size() == 0) {
      aNull = "NULL";
    }
    // 否则，输出所有满足条件的时间段
    else {
      validPeriods.sort(Comparator.comparingInt(a -> a[0]));

      StringJoiner sj = new StringJoiner(" ");
      for (Integer[] period : validPeriods) {
        sj.add(period[0] + "-" + period[1]);
      }
      aNull = sj.toString();
    }
    return aNull;
  }

}
