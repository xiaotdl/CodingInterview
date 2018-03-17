package reverse_linked_list;

/**
 * Created by Xiaotian on 12/31/16.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    // iterative solution
    // tag: linkedlist
    // time: O(n)
    // space: O(1)
    public ListNode reverseList(ListNode head) {
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

class SolutionII {
    // recursive solution
    // tag: linkedlist
    // time: O(n)
    // space: O(1)
    public ListNode reverseList(ListNode head) {
        return dfs(null, head);
    }

    private ListNode dfs(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return dfs(curr, next);
    }
}