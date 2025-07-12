package structures.linkedList;

/**
 * 链表反转 https://leetcode.cn/problems/UHnkqh/
 *
 * @since 2024/9/22
 */
public class ReverseLinkedList {

  /**
   * 单链表的反转
   */
  static ListNode reverse(ListNode list) {
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
