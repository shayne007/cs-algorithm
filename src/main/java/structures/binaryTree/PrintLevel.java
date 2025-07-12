package structures.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO
 *
 * @since 2025/3/18
 */
public class PrintLevel {


  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> level = new LinkedList<>();
    level.offer(root);
    while (level.size() != 0) {
      List<Integer> list = new ArrayList<>();
      final int size = level.size();
      // for循环使用的size，因为level.size()在循环中可能发生变化
      // 所以不能在循环外使用level.size()
      for (int i = 0; i < size; i++) {
        TreeNode node = level.poll();
        if (node.left != null) {
          level.offer(node.left);
        }
        if (node.right != null) {
          level.offer(node.right);
        }

        list.add(node.val);
      }
      result.add(list);
    }
    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println("Tree: " + levelOrder(root));
  }

}
