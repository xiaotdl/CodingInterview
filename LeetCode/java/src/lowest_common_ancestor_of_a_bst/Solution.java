package lowest_common_ancestor_of_a_bst;

/**
 * Created by Xiaotian on 4/26/18.
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
    // tag: bst
    // time: O(logn)
    // space: O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != p && root != q) {
            if (p.val <= root.val && q.val <= root.val) {
                root = root.left;
            }
            else if (p.val >= root.val && q.val >= root.val) {
                root = root.right;
            }
            else break;
        }
        return root;
    }
}

