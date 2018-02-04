package add_two_numbers;

/**
 * Created by Xiaotian on 12/31/16.
 */
/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    // tag: linkedlist
    // time: O(n)
    // space: O(1)
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


class SolutionII {
    /*
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public ListNode addLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        int carry = 0;
        int digit = 0;
        // for (; l1 != null || l2 != null; l1 = l1.next, l2 = l2.next) { // l.next will throw NPE
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0)
                    + (l2 != null ? l2.val : 0)
                    + carry;
            digit = sum % 10;
            carry = sum / 10;

            ListNode curr = new ListNode(digit);
            prev.next = curr;
            prev = curr;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry == 1) {
            prev.next = new ListNode(1);
        }

        return dummy.next;
    }
}
