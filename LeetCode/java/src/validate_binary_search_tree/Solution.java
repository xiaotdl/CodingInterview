package validate_binary_search_tree;

/**
 * Created by Xiaotian on 6/16/17.
 */
public class Solution {
    // tag: dfs
    // time: O(n)
    // space: O(1)
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isValidBST(root.left, min, root.val)
            && isValidBST(root.right, root.val, max);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
