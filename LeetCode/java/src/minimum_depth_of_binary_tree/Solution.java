package minimum_depth_of_binary_tree;

/**
 * Created by Xiaotian on 1/3/17.
 */
// tag: tree, dfs
// time: O(n)
// space: O(1)
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // bottom-up
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);

        if (leftHeight == 0 && rightHeight == 0) return 1;
        else if (leftHeight == 0) return rightHeight + 1;
        else if (rightHeight == 0) return leftHeight + 1;
        else return Math.min(leftHeight, rightHeight) + 1;
    }
}
