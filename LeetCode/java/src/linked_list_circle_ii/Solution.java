package linked_list_circle_ii;

/**
 * Created by Xiaotian on 6/16/17.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    // Assume circle length: L
    // Assume start->entry distance: a, entry->met distance: b, met->entry distance: c
    // When slow met fast, slow went a+b steps, fast went a+b+n*L
    // 2*(a+b) = a+b+n*L => a = n*L - b = (n - 1)*L + c
    // thus when ptr1 go from head and ptr2 go from slow/fast met point, they will meet at circle's entry point
    // Ref: http://blog.csdn.net/kenden23/article/details/13871699
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode p1 = head;
                ListNode p2 = slow;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            };
        }
        return null;
    }
}
