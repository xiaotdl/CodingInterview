package invert_binary_tree;

/**
 * Created by Xiaotian on 7/20/16.
 */
public class Solution {
    // tag: Tree, DFS
    // time: O(n), touched each node once.
    // space: O(logn), depth of the binary tree.
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
