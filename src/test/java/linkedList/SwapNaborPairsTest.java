package linkedList;

import org.junit.Assert;
import org.junit.Test;
import structures.linkedList.ListNode;
import structures.linkedList.SwapNaborPairs;

public class SwapNaborPairsTest {

  @Test
  public void swapPairs() {
    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));

    Assert.assertEquals(ListNode.toString(SwapNaborPairs.swapPairs(head)), "2143");
  }
}