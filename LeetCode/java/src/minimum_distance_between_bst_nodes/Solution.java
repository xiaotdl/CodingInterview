package minimum_distance_between_bst_nodes;

/**
 * Created by Xiaotian on 2/20/18.
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
    int minDiff;
    Integer prevVal;
    public int minDiffInBST(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        prevVal = null;
        dfs(root);
        return minDiff;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        if (prevVal != null) {
            minDiff = Math.min(minDiff, root.val - prevVal);
        }
        prevVal = root.val;
        dfs(root.right);
    }
}
