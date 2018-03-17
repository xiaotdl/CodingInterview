package intersection_of_two_linked_lists;

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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        if (lenA > lenB) {
            for (int i = 1; i <= lenA - lenB; i++) {
                headA = headA.next;
            }
        }
        else if (lenA < lenB) {
            for (int i = 1; i <= lenB - lenA; i++) {
                headB = headB.next;
            }
        }
        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}

class SolutionII {
    // if there is intersection: a and b will meet at intersection in second iteration
    // if there isn't intersection: a and b will meet at the end of each list in second iteration
    // tag: linkedlist
    // time: O(n)
    // space: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // what if there is circle in LinkedList A
        //   1. use slow/fast pointers to detect circle
        //   2. use seen hashset to detect circle

        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != currB) {
            currA = (currA != null ? currA.next : headB);
            currB = (currB != null ? currB.next : headA);
        }
        return currA;
    }
}
