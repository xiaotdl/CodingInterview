package plus_one_linked_list;

/**
 * Created by Xiaotian on 12/31/16.
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

    public ListNode plusOne(ListNode head) {
        head = reverse(head);
        int carry = 1;
        ListNode curr = head;
        ListNode tail = curr;
        while (curr != null) {
            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;
            tail = curr;
            curr = curr.next;
        }
        if (carry == 1) tail.next = new ListNode(1);
        return reverse(head);
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev; // new head
    }
}
