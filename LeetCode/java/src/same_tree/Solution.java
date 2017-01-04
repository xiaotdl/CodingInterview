package same_tree;

/**
 * Created by Xiaotian on 1/4/17.
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

    // top-down
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null) return false;
        else if (q == null) return false;
        else return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
