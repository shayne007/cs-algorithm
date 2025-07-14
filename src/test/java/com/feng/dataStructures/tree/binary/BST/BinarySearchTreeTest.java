package com.feng.dataStructures.tree.binary.BST;

import structures.tree.binary.BST.BinarySearchTree;
import structures.tree.binary.TreeNode;
import org.junit.Assert;
import org.junit.Test;

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