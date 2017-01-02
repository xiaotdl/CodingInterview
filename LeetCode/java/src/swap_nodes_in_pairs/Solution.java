package swap_nodes_in_pairs;

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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            // swap
            ListNode next = curr.next;
            curr.next = next.next;
            prev.next = next;
            next.next = curr;
            // move on
            prev = curr;
            if (curr == null || curr.next == null) break;
            curr = curr.next;
            next = curr.next;
        }
        return dummy.next;
    }
}
