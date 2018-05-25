package diameter_of_binary_tree;

/**
 * Created by Xiaotian on 5/5/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // tag: dfs
    // time: O(n)
    // space: O(1)
    int maxCnt; // max cnt of nodes
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        maxCnt = 0;
        dfs(root);
        return maxCnt - 1;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int lH = dfs(root.left);
        int rH = dfs(root.right);
        maxCnt = Math.max(maxCnt, lH + 1 + rH);

        return Math.max(lH, rH) + 1;
    }
}