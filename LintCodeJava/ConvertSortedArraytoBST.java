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
public class ConvertSortedArraytoBST {
    /**
     * @param A: an integer array
     * @return: a tree node
     */

    // V1, O(n)
    // InorderTraversal, Recursion
    public TreeNode sortedArrayToBST(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        index = 0;
        return sortedArrayToBSTHelper(A, A.length);
    }
    private int index;
    private TreeNode sortedArrayToBSTHelper(int[] A, int length) {
        if (length <= 0) {
            return null;
        }

        TreeNode left = sortedArrayToBSTHelper(A, length / 2);
        TreeNode root = new TreeNode(A[index++]);
        TreeNode right = sortedArrayToBSTHelper(A, length - 1 - length / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}


