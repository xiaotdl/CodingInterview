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

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /**
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */

    // V1, O(n)
    // Recursion
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i); // Assume that duplicates do not exist in the tree.
        }

        return helper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    private TreeNode helper(int[] postorder, int postL, int postR, int[] inorder, int inL, int inR, HashMap<Integer, Integer> map) {
        if (postL > postR || inL > inR) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postR]);
        int index = map.get(root.val);

        root.left = helper(postorder, postL, postL + index - inL - 1, inorder, inL, index - 1, map);
        root.right = helper(postorder, postL + index - inL, postR - 1, inorder, index + 1, inR, map);

        return root;
    }
}

