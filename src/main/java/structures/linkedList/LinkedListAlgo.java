package structures.linkedList;

/**
 * @author fengsy
 * @date 4/19/20
 * @Description
 */
public class LinkedListAlgo {

  /**
   * 单链表删除某个节点
   */
  private ListNode deleteNode(ListNode list, int value) {
    ListNode n = list;
    if (n.data == value) {
      return n.next;
    }
    while (n.next != null) {
      if (n.next.data == value) {
        n.next = n.next.next;
        return list;
      }
      n = n.next;
    }
    return list;
  }

  /**
   * 添加节点至末尾
   */
  private ListNode appendToTail(ListNode list, int value) {
    ListNode n = list;
    ListNode newListNode = new ListNode(value, null);
    if (n == null) {
      return newListNode;
    }
    while (n.next != null) {
      n = n.next;
    }
    n.next = newListNode;
    return list;
  }



  public static ListNode createNode(int value) {
    return new ListNode(value, null);
  }

}


