package algorithms.dynamicProgramming;

/**
 * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
 *
 * @since 2025/3/20
 */
public class BagProblem {
  // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
  static int[] weights = {1, 2, 3, 4, 5};
  static int maxW = Integer.MIN_VALUE; // 结果放到maxW中
  static int num = 5; // 物品个数
  static int weightLimit = 7; // 背包承受的最大重量

  public static int backtrack() {
    recursiveFrom(0, 0); // 调用f(0, 0)
    return maxW;
  }
  // 递归函数，参数i表示当前考察的物品编号，参数cw表示当前背包的重量
  public static void recursiveFrom(int i, int cw) { // 调用f(0, 0)
    if (cw == weightLimit || i == num) { // cw==w表示装满了，i==n表示物品都考察完了
      if (cw > maxW) maxW = cw;
      return;
    }
    recursiveFrom(i+1, cw); // 选择不装第i个物品
    if (cw + weights[i] <= weightLimit) {
      recursiveFrom(i+1,cw + weights[i]); // 选择装第i个物品
    }
  }
  /**
   *  背包问题的动态规划解法
   *   状态转移方程：dp[i][j] = max(dp[i-1][j], dp[i-1][j-weights[i-1]] + weights[i-1])
   *   其中，dp[i][j]表示前i件物品恰好装入一个容量为j的背包可以获得的最大重量，weights[i-1]表示第i件物品的重量。
   *   时间复杂度：O(n*capacity)，其中n为物品个数，capacity为背包容量。
   *
   * @param weights
   * @param capacity
   * @return
   */
  public static int bagProblem(int[] weights, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][capacity + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= capacity; j++) {
        if (j < weights[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + weights[i - 1]);
        }
      }
    }
    return dp[n][capacity];
  }

  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 4, 5};
    int capacity = 7;
    int maxWeight = bagProblem(weights, capacity);
    int maxWeight2 = backtrack();
    System.out.println("Max weight: " + maxWeight);
    System.out.println("Max weight: " + maxWeight2);
  }
}
