/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class ConvertSortedListToBST {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */

    // V1, O(n)
    // InorderTraversal, Recursion
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = getListLength(head);
        curr = head;
        return sortedListToBSTHelper(size);
    }
    private ListNode curr;
    private int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }
    private TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) {
            return null;
        }

        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}


