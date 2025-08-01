package structures.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 *
 * @since 2025/3/18
 */
public class CalculateDepth {

  public int maxDepth(TreeNode root) {
    return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  public int maxDepth2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int depth = 0;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      depth++;
      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }
    return depth;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    // 测试递归解法
    System.out.println("递归解法深度: " + new CalculateDepth().maxDepth(root));
    // 测试BFS解法
    System.out.println("BFS解法深度: " + new CalculateDepth().maxDepth2(root));
  }

}
