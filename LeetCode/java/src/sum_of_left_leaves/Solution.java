package sum_of_left_leaves;

/**
 * Created by Xiaotian on 1/4/17.
 */
// tag: tree, dfs
// time: O(n)
// space: O(1)
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    // bottom-up
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return helper(root.left, true) + helper(root.right, false);
    }

    public int helper(TreeNode root, boolean isLeftSubtree) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isLeftSubtree) return root.val;
        return helper(root.left, true) + helper(root.right, false);
    }
}
