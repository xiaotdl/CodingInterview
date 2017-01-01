package reverse_linked_list;

/**
 * Created by Xiaotian on 12/31/16.
 */
// iterative solution
// tag: linkedlist
// time: O(n)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

// recursive solution
// tag: linkedlist
// time: O(n)
// space: O(1)
class SolutionII {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        return helper(null, head);
    }

    public ListNode helper(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return helper(curr, next);
    }
}