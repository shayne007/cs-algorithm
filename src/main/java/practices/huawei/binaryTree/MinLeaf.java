package practices.huawei.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 最小叶子节点的遍历路径
 *
 * @since 2025/7/13
 */
public class MinLeaf {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Integer[] arr =
        Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

    System.out.println(getResult(arr));
  }

  public static String getResult(Integer[] arr) {
    int n = arr.length - 1;

    // 最小叶子节点的值
    int min = Integer.MAX_VALUE;
    // 最小叶子节点的索引
    int minIdx = -1;

    // 求解最小叶子节点的值和索引
    for (int i = n; i >= 1; i--) {
      if (arr[i] != -1) {
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
    LinkedList<Integer> path = new LinkedList<>();
    path.addFirst(min);

    // 从最小叶子节点开始向上找父节点，直到树顶
    while (minIdx != 0) {
      int f = (minIdx - 1) / 2;
      path.addFirst(arr[f]);
      minIdx = f;
    }

    StringJoiner sj = new StringJoiner(" ");
    for (Integer val : path) {
      sj.add(val + "");
    }

    return sj.toString();
  }
}
