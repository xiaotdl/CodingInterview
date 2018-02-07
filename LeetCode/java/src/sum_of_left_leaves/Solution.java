package sum_of_left_leaves;

/**
 * Created by Xiaotian on 1/4/17.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

public class Solution {
    // tag: binary tree, dfs
    // time: O(n)
    // space: O(1)
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

class SolutionII {
    // DFS, 从上往下传通过参数，从下往上传通过返回值
    // tag: binary tree, dfs
    // time: O(n)
    // space: O(1)
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, false);
    }

    private int dfs(TreeNode root, boolean isLeft) {
        if (root.left == null && root.right == null // isLeaf
            && isLeft) {
            return root.val;
        }

        return (root.left != null ? dfs(root.left, true) : 0)
            + (root.right != null ? dfs(root.right, false) : 0);
    }
}