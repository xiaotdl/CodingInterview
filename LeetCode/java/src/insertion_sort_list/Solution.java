package insertion_sort_list;

/**
 * Created by Xiaotian on 1/1/17.
 */
// tag: linkedlist
// time: O(n^2)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode ptr = dummy;
        ListNode prev = head; // tail of sorted list
        ListNode curr = head.next; // the node to be inserted between ptr and ptr.next
        while (curr != null) {
            // locate ptr and ptr.next, find first smallerOrEq as ptr
            while (ptr.next != null && ptr.next.val < curr.val) {
                ptr = ptr.next;
            }
            if (ptr.next == curr) {
                ptr = dummy;
                prev = curr;
                curr = curr.next;
            }
            else {
                // take out curr
                prev.next = curr.next;
                // insert curr
                curr.next = ptr.next;
                ptr.next = curr;
                // move on
                curr = prev.next;
            }
            ptr = dummy;
        }
        return dummy.next;
    }
}

