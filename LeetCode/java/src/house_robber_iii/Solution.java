package house_robber_iii;

/**
 * Created by xili on 7/21/16.
 */
public class Solution {
    // tag: DFS
    // time: O(2^n), lots of repeated calculations
    // space: O(1), no additional space
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(root.val + val, rob(root.left) + rob(root.right));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
