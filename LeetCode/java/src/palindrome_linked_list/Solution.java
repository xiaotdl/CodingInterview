package palindrome_linked_list;

/**
 * Created by Xiaotian on 5/3/18.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    // credit: https://www.jiuzhang.com/solution/palindrome-linked-list/
    // find mid, reverse second half, compare first half vs. second half
    // tag: ptr
    // time: O(n)
    // space: O(1), in place
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        ListNode mid = findMid(head);
        mid.next = reverse(mid.next);

        ListNode p1 = head;
        ListNode p2 = mid.next;
        while (p1 != null && p2 != null && p1.val == p2.val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2 == null;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
