/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */
public class InsertionSortList {
    /**
     * @param head: The first node of linked list.
     * @return: The head of linked list.
     */

    // V1, O(n)
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            prev = dummy;
            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }
            ListNode tmp = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = tmp;
        }

        return dummy.next;
    }
}
