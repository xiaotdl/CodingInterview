package swap_nodes_in_pairs;

/**
 * Created by Xiaotian on 1/1/17.
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            // swap
            ListNode next = curr.next;
            curr.next = next.next;
            prev.next = next;
            next.next = curr;
            // move on
            prev = curr;
            if (curr == null || curr.next == null) break;
            curr = curr.next;
            next = curr.next;
        }
        return dummy.next;
    }
}

class SolutionII {
    // tag: linkedlist, iteration
    // time: O(n)
    // space: O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy;
        ListNode c1 = head;
        ListNode c2 = head.next;
        ListNode n = (head.next != null ? head.next.next : null);
        while (c2 != null) {
            p.next = c2;
            c2.next = c1;
            c1.next = n;

            p = c1;
            c1 = p.next;
            if (c1 == null) break;
            c2 = c1.next;
            if (c2 == null) break;
            n = c2.next;
        }
        return dummy.next;
    }
}

class SolutionIII {
    // tag: linkedlist, recursion
    // time: O(n)
    // space: O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = head.next;
        ListNode nextHead = head.next.next;
        newHead.next = head;
        head.next = swapPairs(nextHead);
        return newHead;
    }
}
