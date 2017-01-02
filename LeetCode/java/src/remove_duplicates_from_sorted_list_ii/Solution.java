package remove_duplicates_from_sorted_list_ii;

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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            while (curr != null & curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }
            if (prev.next != curr) {
                prev.next = curr.next;
                curr = prev.next;
            }
            else {
                prev = prev.next;
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
