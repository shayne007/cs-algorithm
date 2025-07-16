package com.feng.dataStructures.tree.binary.BST;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import structures.tree.binary.BST.BinarySearchTree;
import structures.tree.binary.TreeNode;

public class BinarySearchTreeTest {

  @Test
  public void testIsValidBST() {
    TreeNode tree = new TreeNode(2);
    tree.left = new TreeNode(1);
    tree.right = new TreeNode(3);
    BinarySearchTree bst = new BinarySearchTree(tree);
    Assert.assertTrue(bst.isValidBST());
  }
}