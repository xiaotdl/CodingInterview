package add_two_numbers_ii;

/**
 * Created by Xiaotian on 12/31/16.
 */

import java.util.Stack;

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
        l1 = reverse(l1);
        l2 = reverse(l2);
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
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

// tag: linkedlist, stack
// time: O(n)
// space: O(1)
class SolutionII {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        int digit = 0;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode tail = dummy;
        while (!s1.empty() || !s2.empty()) {
            if (s1.empty()) {
                sum = s2.pop() + carry;
            }
            else if (s2.empty()) {
                sum = s1.pop() + carry;
            }
            else {
                sum = s1.pop() + s2.pop() + carry;
            }
            digit = sum % 10;
            carry = sum / 10;
            curr.next = new ListNode(digit);
            curr = curr.next;
            tail = curr;
        }
        if (carry == 1) tail.next = new ListNode(1);
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
