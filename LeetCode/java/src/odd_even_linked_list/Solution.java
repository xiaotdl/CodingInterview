package odd_even_linked_list;

/**
 * Created by Xiaotian on 1/1/17.
 */
// tag: linkedlist
// time: O(n)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode even = head.next;
        ListNode prevEvenHead = head;
        ListNode evenHead = even;
        ListNode odd = head.next.next;
        while (odd != null) {
            // insert odd node before evenHead
            even.next = odd.next;
            prevEvenHead.next = odd;
            odd.next = evenHead;
            // move on
            prevEvenHead = odd;
            even = even.next;
            if (even == null) break;
            odd = even.next;
        }
        return head;
    }
}
