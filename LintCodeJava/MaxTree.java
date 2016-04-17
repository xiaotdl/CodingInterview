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
public class MaxTree {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */

    // V1, O(n)
    // Decremental Stack
    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();

        for (int i = 0; i < A.length; i++) {
            TreeNode curr = new TreeNode(A[i]);
            while (!stack.isEmpty() && curr.val > stack.peek().val) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }

        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }

        return root;
    }

    // V2, O(nlogn)
    // Recursion

    // V3, O(nlogn)
    // Max Heap
}

