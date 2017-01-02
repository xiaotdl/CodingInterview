package sort_list;

/**
 * Created by Xiaotian on 1/1/17.
 */
// tag: linkedlist, merge sort
// time: O(nlogn)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // cut the list into two halves
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        // sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);

        // merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                curr.next = l2;
                l2 = l2.next;
            }
            else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 :
                    l2 != null ? l2 :
                               null;
        return dummy.next;
    }
}
