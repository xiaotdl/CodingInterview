/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RotateList {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */

    // V1, O(n)
    // fast slow ptrs
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int length = getLength(head);
        k = k % length;

        if (k == 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }
    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}

