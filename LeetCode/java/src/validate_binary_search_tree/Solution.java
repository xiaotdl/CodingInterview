package validate_binary_search_tree;

/**
 * Created by Xiaotian on 6/16/17.
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

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

class SolutionII {
    // inorder traversal is increasing order
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        prev = null;
        return dfs(root);
    }

    private boolean dfs(TreeNode curr) {
        if (curr == null) return true;
        if (!dfs(curr.left)) return false;
        if (prev != null && curr.val <= prev.val) return false;
        prev = curr;
        if (!dfs(curr.right)) return false;;
        return true;
    }
}

