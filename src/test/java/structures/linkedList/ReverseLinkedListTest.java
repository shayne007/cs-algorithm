package structures.linkedList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseLinkedListTest {

  @Test
  public void reverse() {
    ListNode listNode1 = createNode(3);
    ListNode listNode2 = createNode(4);
    listNode1.next = listNode2;
    ListNode list = new ListNode(-1, listNode1);

    printAll(list);

    ListNode revlist = ReverseLinkedList.reverse(list);

    printAll(revlist);

    assertEquals(4, revlist.data);
    assertEquals(3, revlist.next.data);
    assertEquals(-1, revlist.next.next.data);
  }
  public static void printAll(ListNode list) {
    ListNode p = list;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }
  public static ListNode createNode(int value) {
    return new ListNode(value, null);
  }

}