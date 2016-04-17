import java.util.ArrayList;

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
public class SearchRangeBST {
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        helper(root, k1, k2, result);
        return result;
    }

    private void helper(TreeNode root, int k1, int k2, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }

        if (root.val > k1) {
            helper(root.left, k1, k2, result);
        }
        if (k1 <= root.val && root.val <= k2) {
            result.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2, result);
        }
    }
}

