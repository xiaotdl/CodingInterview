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
public class BinaryTreeMaxPathSum {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */

    // V1, O(n)
    // Divide&Conquer
    // path: root-to-any, could include nothing
    // maxPath: any-to-any, include at least current root
    public int maxPathSum(TreeNode root) {
        Result result = helper(root);
        return result.maxPath;
    }

    private class Result {
        int path, maxPath;
        Result(int path, int maxPath) {
            this.path = path;
            this.maxPath = maxPath;
        }
    }

    private Result helper(TreeNode root) {
        if (root == null) {
            return new Result(0, Integer.MIN_VALUE);
        }

        // Divide
        Result left = helper(root.left);
        Result right = helper(root.right);

        // Conquer
        int path = Math.max(0, Math.max(left.path, right.path) + root.val);
        int maxPath = Math.max(Math.max(left.maxPath, right.maxPath),
                left.path + right.path + root.val);

        return new Result(path, maxPath);
    }
}

