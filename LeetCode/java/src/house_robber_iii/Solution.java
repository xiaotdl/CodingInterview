package house_robber_iii;

/**
 * Created by xili on 7/21/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // tag: dfs, top-down
    // time: O(n^2)
    // space: O(1)
    public int rob(TreeNode root) {
        if (root == null) return 0;

        // rob curr
        int money1 = root.val
                + (root.left != null ? rob(root.left.left) + rob(root.left.right) : 0)
                + (root.right != null ? rob(root.right.left) + rob(root.right.right) : 0);

        // doesn't rob curr
        int money2 = rob(root.left) + rob(root.right);

        return Math.max(money1, money2);
    }
}

class SolutionII {
    // saves both rob/not rob value, thus much faster than SolutionI
    // tag: dfs, bottom-up, post-order
    // time: O(n)
    // space: O(1)
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dfs(TreeNode root) {
        // r[0]: doesn't rob root
        // r[1]: rob root
        int[] res = new int[2];
        if (root == null) return res;

        int[] lMax = dfs(root.left);
        int[] rMax = dfs(root.right);
        res[0] = Math.max(lMax[0], lMax[1]) + Math.max(rMax[0], rMax[1]);
        res[1] = root.val + lMax[0] + rMax[0];
        return res;
    }
}
