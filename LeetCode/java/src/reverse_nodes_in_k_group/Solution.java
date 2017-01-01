package reverse_nodes_in_k_group;

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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        ListNode rPrev = dummy; // reversePrev
        ListNode rTail = dummy; // reverseTail
        ListNode toInsert = null;
        int count;
        while (true) {
            count = k;
            while (count > 0 && tail != null) {
                count--;
                tail = tail.next;
            }
            if (tail == null) break;

            rTail = rPrev.next;
            for (int i = 0; i < k - 1; i++) {
                toInsert = rTail.next;
                rTail.next = toInsert.next;
                toInsert.next = rPrev.next;
                rPrev.next = toInsert;
            }
            rPrev = rTail;
            tail = rTail;
        }
        return dummy.next;
    }
}

