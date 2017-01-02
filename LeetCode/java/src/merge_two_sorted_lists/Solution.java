package merge_two_sorted_lists;

/**
 * Created by Xiaotian on 1/1/17.
 */
// tag: linkedlist, two ptr
// time: O(n)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
