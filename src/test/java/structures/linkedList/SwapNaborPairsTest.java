package structures.linkedList;

import org.junit.Assert;
import org.junit.Test;

public class SwapNaborPairsTest {

  @Test
  public void swapPairs() {
    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));

    Assert.assertEquals(ListNode.toString(SwapNaborPairs.swapPairs(head)), "2143");
  }
}