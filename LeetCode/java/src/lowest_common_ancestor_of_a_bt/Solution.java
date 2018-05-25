package lowest_common_ancestor_of_a_bt;

/**
 * Created by Xiaotian on 2/7/18.
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    // tag: binary tree, dfs
    // time: O(n)
    // space: O(n)
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;
        return dfs(root, A, B);
    }

    private TreeNode dfs(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || root == A || root == B) return root;

        TreeNode left = dfs(root.left, A, B);
        TreeNode right = dfs(root.right, A, B);

        if (left != null && right != null) return root;
        else if (left != null) return left;
        else if (right != null) return right;
        else return null;
    }
}
