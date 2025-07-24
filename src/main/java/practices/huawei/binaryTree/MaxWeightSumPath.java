package practices.huawei.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 传递悄悄话, 求解到叶子节点的权重之和最大的路径，输出该路径的权重之和
 * <p>
 * 题目描述 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
 * 初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
 * <p>
 * 输入描述 给定二叉树,注：-1表示空节点，根节点值为0
 * <p>
 * 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
 * <p>
 * 输出描述 返回所有节点都接收到悄悄话花费的时间
 *
 * @since 2025/7/12
 */
public class MaxWeightSumPath {

  public static void main(String[] args) {
    // 创建扫描器读取输入
    Scanner scanner = new Scanner(System.in);
    // 读取一行输入并将其转换为整数数组，数组中的每个元素代表从父节点到当前节点的时间
    int[] whisperTimes = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
        .toArray();
    // 关闭扫描器
    scanner.close();

    // 所有节点都接收到悄悄话后，打印最大时间
    System.out.println(getMaxTime(whisperTimes));
  }

  /**
   * 二叉树的层次遍历
   *
   * @param binaryArr 二叉树节点时间数组
   * @return 最大时间和
   */
  private static int getMaxTime(int[] binaryArr) {
    // 记录最大时间和
    int maxTime = 0;
    // 使用队列来进行二叉树的层次遍历
    Queue<Integer> nodeQueue = new LinkedList<>();
    // 将根节点索引0加入队列
    nodeQueue.add(0);

    // 当队列不为空时，继续遍历
    while (!nodeQueue.isEmpty()) {
      // 从队列中取出一个节点索引
      int curNodeIdx = nodeQueue.poll();// poll()方法移除并返回队列头部的元素，no exception if queue is empty
      // 计算左子节点索引
      int leftChildIdx = 2 * curNodeIdx + 1;
      // 计算右子节点索引
      int rightChildIdx = 2 * curNodeIdx + 2;

      // 如果左子节点存在，处理左子节点
      if (leftChildIdx < binaryArr.length && binaryArr[leftChildIdx] != -1) {
        // 更新左子节点的时间（父节点时间 + 当前节点时间）
        binaryArr[leftChildIdx] += binaryArr[curNodeIdx];// 利用原数组存储已经遍历过的节点的计算结果
        // 将左子节点加入队列
        nodeQueue.add(leftChildIdx);
        // 更新最大时间
        maxTime = Math.max(maxTime, binaryArr[leftChildIdx]);
      }

      // 如果右子节点存在，处理右子节点
      if (rightChildIdx < binaryArr.length && binaryArr[rightChildIdx] != -1) {
        // 更新右子节点的时间（父节点时间 + 当前节点时间）
        binaryArr[rightChildIdx] += binaryArr[curNodeIdx];
        // 将右子节点加入队列
        nodeQueue.add(rightChildIdx);
        // 更新最大时间
        maxTime = Math.max(maxTime, binaryArr[rightChildIdx]);
      }
    }
    return maxTime;
  }
}
