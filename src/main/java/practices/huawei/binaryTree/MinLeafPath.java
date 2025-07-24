package practices.huawei.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 数组二叉树
 * <p>
 * 题目描述 二叉树也可以用数组来存储，给定一个数组，树的根节点的值存储在下标1，对于存储在下标N的节点，它的左子节点和右子节点分别存储在下标2*N和2*N+1，并且我们用值-1代表一个节点为空。
 * 给定一个数组存储的二叉树，试求从根节点到最小的叶子节点的路径，路径由节点的值组成。
 * <p>
 * 输入描述 输入一行为数组的内容，数组的每个元素都是正整数，元素间用空格分隔。 注意第一个元素即为根节点的值，即数组的第N个元素对应下标N，下标0在树的表示中没有使用，所以我们省略了。
 * 输入的树最多为7层。
 * <p>
 * 输出描述 输出从根节点到最小叶子节点的路径上，各个节点的值，由空格分隔，用例保证最小叶子节点只有一个。
 * <p>
 * 用例 输入	3 5 7 -1 -1 2 4
 * <p>
 * 输出	3 7 2 说明	最小叶子节点的路径为3 7 2。
 * <p>
 * 输入	5 9 8 -1 -1 7 -1 -1 -1 -1 -1 6
 * <p>
 * 输出	5 8 7 6
 * <p>
 * 说明	最小叶子节点的路径为5 8 7 6，注意数组仅存储至最后一个非空节点，故不包含节点“7”右子节点的-1。
 *
 * @since 2025/7/13
 */
public class MinLeafPath {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // 二叉树的数组表示：3 5 7 -1 -1 2 4
    Integer[] arr =
        Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
    // 求解到叶子节点值最小的路径
    System.out.println(getMinLeafPath(arr));
  }

  public static String getMinLeafPath(Integer[] arr) {
    int n = arr.length - 1;
    // 最小叶子节点的值
    int min = Integer.MAX_VALUE;
    // 最小叶子节点的索引
    int minIdx = -1;

    // 求解最小叶子节点的值和索引
    for (int i = n; i >= 1; i--) {
      if (arr[i] != -1) { // 自身节点值不为-1
        // 自身没有子节点（即既没有左子节点，也没有右子节点）
        // 对于存储在下标N的节点(根节点0)，它的左子节点和右子节点分别存储在下标2*N+1和2*N+2
        if (i * 2 + 1 <= n && arr[i * 2 + 1] != -1) {
          continue;
        }
        if (i * 2 + 2 <= n && arr[i * 2 + 2] != -1) {
          continue;
        }
        if (min > arr[i]) {
          min = arr[i];
          minIdx = i;
        }
      }
    }

    // path用于缓存最小叶子节点到根的路径
    LinkedList<Integer> path = getPath(arr, min, minIdx);
    // 将LinkedList<Integer>转换为空格拼接的String输出
    return convertToString(path);
  }

  private static LinkedList<Integer> getPath(Integer[] arr, int min, int minIdx) {
    LinkedList<Integer> path = new LinkedList<>();
    path.addFirst(min);
    // 从最小叶子节点开始向上找父节点，直到树顶
    while (minIdx != 0) {
      int parentIdx = (minIdx - 1) / 2; // 父节点索引: (当前节点索引-1)除2取整
      path.addFirst(arr[parentIdx]);
      minIdx = parentIdx;
    }
    return path;
  }

  private static String convertToString(LinkedList<Integer> path) {
    return path.stream().map(String::valueOf).collect(Collectors.joining(" "));
  }
}
