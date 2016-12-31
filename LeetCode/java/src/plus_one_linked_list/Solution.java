package plus_one_linked_list;

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

    public ListNode plusOne(ListNode head) {
        head = reverse(head);
        int carry = 1;
        ListNode node = head;
        ListNode tail = node;
        while (node != null) {
            int sum = node.val + carry;
            node.val = sum % 10;
            carry = sum / 10;
            tail = node;
            node = node.next;
        }
        if (carry == 1) tail.next = new ListNode(1);
        return reverse(head);
    }

    public ListNode reverse(ListNode head) {
        ListNode currNode = head;
        ListNode nextNode = null;
        ListNode prevNode = null;
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode; // new head
    }
}
