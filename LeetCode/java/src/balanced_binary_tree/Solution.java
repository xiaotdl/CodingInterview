package balanced_binary_tree;

/**
 * Created by Xiaotian on 1/2/17.
 */
// tag: tree, dfs
// time: O(n^2)
// space: O(1)
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // top-down
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
            && isBalanced(root.left)
            && isBalanced(root.right);
    }

    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;

    }
}

// tag: tree, dfs
// time: O(n)
// space: O(1)
class SolutionII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // bottom-up
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return validateBalanced(root) != -1;
    }

    // Return node.balanced ? node.height : -1;
    public int validateBalanced(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = validateBalanced(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = validateBalanced(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
