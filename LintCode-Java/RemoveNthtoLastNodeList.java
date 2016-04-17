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
public class RemoveNthtoLastNodeList {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */

    // V1, O(n)
    // two pointers
    ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preDelete = dummy;

        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }

        while (head != null) {
            preDelete = preDelete.next;
            head = head.next;
        }

        preDelete.next = preDelete.next.next;
        return dummy.next;
    }
}


