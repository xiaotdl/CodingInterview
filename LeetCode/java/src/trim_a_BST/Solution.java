package trim_a_BST;

/**
 * Created by Xiaotian on 3/1/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: binary tree, dfs
    // time: O(n)
    // space: O(1)
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        // check root: trim out of range nodes
        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);
        // check root.left and root.right: trim left and right sub nodes
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
