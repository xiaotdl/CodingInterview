package two_sum_bst;

/**
 * Created by Xiaotian on 2/11/18.
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    // tag: ptr
    // time: O(nlogn)
    // space: O(1)
    /*
     * @param : the root of tree
     * @param : the target sum
     * @return: two numbers from tree which sum is n
     */
    public int[] twoSum(TreeNode root, int target) {
        if (root == null) return null;

        TreeNode l = getMinNode(root);
        TreeNode r = getMaxNode(root);
        while (l.val < r.val) {
            if (l.val + r.val == target) {
                return new int[]{l.val, r.val};
            }
            else if (l.val + r.val < target) {
                l = getNext(root, l);
            }
            else {
                r = getPrev(root, r);
            }
        }
        return new int[]{};
    }

    private TreeNode getMinNode(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private TreeNode getMaxNode(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    private TreeNode getNext(TreeNode root, TreeNode n) {
        // next is under node
        if (n.right != null) return getMinNode(n.right);

        // next is above node
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > n.val) {
                prev = curr;
                curr = curr.left;
            }
            else if (curr.val < n.val) {
                curr = curr.right;
            }
            else {
                return prev;
            }
        }
        return null;
    }

    private TreeNode getPrev(TreeNode root, TreeNode n) {
        // prev is under node
        if (n.left != null) return getMaxNode(n.left);

        // prev is above node
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > n.val) {
                curr = curr.left;
            }
            else if (curr.val < n.val) {
                prev = curr;
                curr = curr.right;
            }
            else {
                return prev;
            }
        }
        return null;
    }
}
