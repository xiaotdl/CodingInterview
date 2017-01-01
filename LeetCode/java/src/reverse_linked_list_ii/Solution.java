package reverse_linked_list_ii;

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

    // reversePrev->reverseHead->reverseTail->reverseNext
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int l = n - m + 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (m - 1 > 0) {
            if (curr == null) return dummy.next;
            prev = curr;
            curr = curr.next;
            m--;
        }
        ListNode reversePrev = prev;
        ListNode reverseTail = curr;
        ListNode next = null;
        while (l > 0) {
            if (curr == null) return dummy.next;
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            l--;
        }
        ListNode reverseHead = prev;
        ListNode reverseNext = curr;
        reversePrev.next = reverseHead;
        reverseTail.next = reverseNext;
        return dummy.next;
    }
}

// tag: linkedlist
// time: O(n)
// space: O(1)
class SolutionII {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 1 -> 2 -> 3 -> 4 -> 5; m=2; n=4
    // step1: 1 -> 3 -> 2 -> 4 -> 5
    // step2: 1 -> 4 -> 3 -> 2 -> 5
    // insert n - m nodes
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int l = n - m;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (m - 1 > 0) {
            if (curr == null) return dummy.next;
            prev = curr;
            curr = curr.next;
            m--;
        }
        ListNode node = head;
        ListNode reversePrev = prev;
        ListNode reverseTail = curr;
        while (l > 0) {
            ListNode toInsert = reverseTail.next;  // locate toInsert node
            reverseTail.next = toInsert.next;      // take out toInsert node
            toInsert.next = reversePrev.next;      // insert toInsert node between reversePrev and reversePrev.next, step1
            reversePrev.next = toInsert;           // insert toInsert node between reversePrev and reversePrev.next, step2
            l--;
        }
        return dummy.next;
    }
}
