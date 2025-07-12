package structures.linkedList;

/**
 * 给定一个单链表，两两交换相邻节点，并返回交换后的链表。
 *
 * @since 2024/9/22
 */
public class SwapNaborPairs {

  /**
   * 单链表交换相邻节点
   */
  static ListNode swapPairs(ListNode list) {
    ListNode curr = list, pre = null;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    return pre;
  }
}
