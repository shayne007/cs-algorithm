package algorithms.dynamicProgramming;

/**
 * LeetCode 152. Maximum Product Subarray https://leetcode.com/problems/maximum-product-subarray/
 * Given an integer array nums, find the contiguous subarray within an array (containing at least
 * one number) which has the largest product.
 * <p>Example 1: Input: [2,3,-2,4]
 * Output: 6 Explanation: [2,3] has the largest product 6.</>
 *
 * @since 2025/3/20
 */
public class MaxProduct {

  public int maxProduct(int[] nums) {
    int length = nums.length;
    long[] maxF = new long[length];
    long[] minF = new long[length];
    for (int i = 0; i < length; i++) {
      maxF[i] = nums[i];
      minF[i] = nums[i];
    }
    for (int i = 1; i < length; ++i) {
      maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
      minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
      if (minF[i] < (-1 << 31)) {
        minF[i] = nums[i];
      }
    }
    int ans = (int) maxF[0];
    for (int i = 1; i < length; ++i) {
      ans = Math.max(ans, (int) maxF[i]);
    }
    return ans;
  }
}
