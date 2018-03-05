package binary_tree_flipping;

/**
 * Created by Xiaotian on 1/31/18.
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
    // 先递归再翻转当前节点
    // tag: binary tree
    // time: O(n)
    // space: O(1)
    private TreeNode newRoot;
    /*
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return newRoot;
    }

    private void dfs(TreeNode root) {
        if (root.left == null) {
            newRoot = root;
            return;
        }

        dfs(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
    }
}

class SolutionII {
    // Similar with Solution
    TreeNode newRoot;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        newRoot = null;
        TreeNode lChild = root.left;
        TreeNode rChild = root.right;
        root.left = null;
        root.right = null;
        dfs(root, lChild, rChild);
        return newRoot;
    }

    private void dfs(TreeNode parent, TreeNode lChild, TreeNode rChild) {
        if (lChild == null && rChild == null) {
            newRoot = parent;
            return;
        }

        TreeNode nextParent = lChild;
        TreeNode nextLChild = nextParent.left;
        TreeNode nextRChild = nextParent.right;

        lChild.left = rChild;
        lChild.right = parent;

        dfs(nextParent, nextLChild, nextRChild);
    }
}