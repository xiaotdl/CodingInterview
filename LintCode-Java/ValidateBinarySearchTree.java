/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class ValidateBinarySearchTree {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */

    // V1, O(n)
    // Divide&Conquer
    // BST:
    // 1. Left tree is BST;
    // 2. Right tree is BST;
    // 3. max value of left subtree < root value < min value of the right sub tree.
    public boolean isValidBST(TreeNode root) {
        Result result = helper(root);
        return result.isBST;
    }

    private Result helper(TreeNode root) {
        Result result = new Result(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        if (root == null) {
            return result;
        }

        // Divide
        Result left = helper(root.left);
        Result right = helper(root.right);

        // Conquer
        if (!left.isBST || !right.isBST) {
            result.isBST = false;
            return result;
        }
        if (root.left != null && root.val <= left.max) {
            result.isBST = false;
            return result;
        }
        if (root.right != null && root.val >= right.min) {
            result.isBST = false;
            return result;
        }
        return new Result(true,
                Math.min(root.val, left.min),
                Math.max(root.val, right.max));
    }

    private class Result {
        boolean isBST;
        int min, max;
        Result (boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    // V2, O(n)
    // Inorder Traversal is ascending <=> BST
    private Integer lastVal = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (lastVal != null && root.val <= lastVal) {
            return false;
        }
        lastVal = root.val;

        if (!isValidBST(root.right)) {
            return false;
        }

        return true;
    }
}

