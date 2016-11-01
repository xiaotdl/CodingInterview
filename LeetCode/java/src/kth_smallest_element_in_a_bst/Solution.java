package kth_smallest_element_in_a_bst;

/**
 * Created by Xiaotian on 10/31/16.
 */
public class Solution {
    // tag: Inorder Traverse
    // time: O(k)
    // space: O(1)
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int result;
    int cnt;
    int k;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        this.k = k;
        inorderTraverse(root);
        return result;
    }

    void inorderTraverse(TreeNode root) {
        if (root == null || cnt >= this.k) {
            return;
        }
        inorderTraverse(root.left);
        cnt++;
        if (cnt == this.k) {
            result = root.val;
        }
        inorderTraverse(root.right);
    }
}
