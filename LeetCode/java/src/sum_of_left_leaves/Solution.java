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
    // DFS, 从上往下传通过参数，从下往上传通过返回值
    // tag: binary tree, dfs
    // time: O(n)
    // space: O(1)
    // bottom-up
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, false);
    }

    public int dfs(TreeNode root, boolean isLeftSubtree) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isLeftSubtree) return root.val;
        return dfs(root.left, true) + dfs(root.right, false);
    }
}
