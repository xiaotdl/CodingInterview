package add_two_numbers;

/**
 * Created by Xiaotian on 12/31/16.
 */
/**
 * Definition for singly-linked list.
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int digit = 0;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode tail = curr;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                sum = l2.val + carry;
                l2 = l2.next;
            }
            else if (l2 == null) {
                sum = l1.val + carry;
                l1 = l1.next;
            }
            else {
                sum = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }
            digit = sum % 10;
            carry = sum / 10;
            curr.next = new ListNode(digit);
            curr = curr.next;
            tail = curr;
        }
        if (carry == 1) tail.next = new ListNode(1);
        return dummy.next;
    }
}
