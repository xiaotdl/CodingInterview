package convert_sorted_list_to_binary_binary_search_tree;

/**
 * Created by Xiaotian on 1/1/17.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: linkedlist
    // time: O(nlogn)
    // space: O(1)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode leftListNode = (head == slow ? null : head);
        ListNode rootListNode = slow;
        ListNode rightListNode = slow.next;
        rootListNode.next = null;
        prev.next = null;
        // debug("left", leftListNode);
        // debug("root", rootListNode);
        // debug("right", rightNode);

        TreeNode root = new TreeNode(rootListNode.val);
        root.left = sortedListToBST(leftListNode);
        root.right = sortedListToBST(rightListNode);

        return root;
    }

    // public void debug(String msg, ListNode head) {
    //     System.out.printf("%s: ", msg);
    //     while (head != null) {
    //         System.out.printf("%s->", head.val);
    //         head = head.next;
    //     }
    //     System.out.printf("null\n");
    // }
}

class SolutionII {
    // tag: linkedlist
    // time: O(nlogn)
    // space: O(1)
    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head, null);
    }

    private TreeNode dfs(ListNode head, ListNode tail) { // [head, tail)
        if (head == tail) return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = dfs(head, slow);
        root.right = dfs(slow.next, tail);
        return root;
    }
}
