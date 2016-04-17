/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveDupSortedListII {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */

    // V1, O(n)
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                int val = curr.val;
                while (curr != null && curr.val == val) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}
