package delete_node_in_a_linked_list;

/**
 * Created by Xiaotian on 12/31/16.
 */
// tag: linkedlist
// time: O(1)
// space: O(1)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        if (node.next == null) node = null;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
