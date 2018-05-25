package subtree_of_another_tree;

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
    // time: O(n*m), n: number of nodes in s, m: number of nodes in t
    // space: O(1)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (isSame(s, t)) return true;
        return isSubtree(s.left, t)
                || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (s.val != t.val) return false;
        return isSame(s.left, t.left)
                && isSame(s.right, t.right);
    }
}

class SolutionII {
    // tag: binary tree
    // time: O(n+m), n: number of nodes in s, m: number of nodes in t
    // space: O(n+m)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // Java uses a naive contains algorithm so to ensure linear time,
        // replace with KMP algorithm
        return serialize(s).contains(serialize(t));
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append(",#");
            return;
        }
        res.append("," + root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}

