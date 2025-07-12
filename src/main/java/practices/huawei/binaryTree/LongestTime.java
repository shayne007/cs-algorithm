package practices.huawei.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/12
 */
public class LongestTime {

  public static void main(String[] args) {
    // 创建扫描器读取输入
    Scanner scanner = new Scanner(System.in);
    // 读取一行输入并将其转换为整数数组，数组中的每个元素代表从父节点到当前节点的时间
    int[] whisperTimes = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
        .toArray();
    // 关闭扫描器
    scanner.close();

    // 记录最后一个节点接收悄悄话的时间
    int maxTime = 0;

    // 使用队列来进行二叉树的层次遍历
    Queue<Integer> nodeQueue = new LinkedList<>();
    // 将根节点索引0加入队列
    nodeQueue.add(0);

    // 当队列不为空时，继续遍历
    while (!nodeQueue.isEmpty()) {
      // 从队列中取出一个节点索引
      int parentNodeIndex = nodeQueue.poll();

      // 计算左子节点索引
      int leftChildIndex = 2 * parentNodeIndex + 1;
      // 计算右子节点索引
      int rightChildIndex = 2 * parentNodeIndex + 2;

      // 如果左子节点存在，处理左子节点
      if (leftChildIndex < whisperTimes.length && whisperTimes[leftChildIndex] != -1) {
        // 更新左子节点的时间（父节点时间 + 当前节点时间）
        whisperTimes[leftChildIndex] += whisperTimes[parentNodeIndex];
        // 将左子节点加入队列
        nodeQueue.add(leftChildIndex);
        // 更新最大时间
        maxTime = Math.max(maxTime, whisperTimes[leftChildIndex]);
      }

      // 如果右子节点存在，处理右子节点
      if (rightChildIndex < whisperTimes.length && whisperTimes[rightChildIndex] != -1) {
        // 更新右子节点的时间（父节点时间 + 当前节点时间）
        whisperTimes[rightChildIndex] += whisperTimes[parentNodeIndex];
        // 将右子节点加入队列
        nodeQueue.add(rightChildIndex);
        // 更新最大时间
        maxTime = Math.max(maxTime, whisperTimes[rightChildIndex]);
      }
    }

    // 所有节点都接收到悄悄话后，打印最大时间
    System.out.println(maxTime);
  }
}
