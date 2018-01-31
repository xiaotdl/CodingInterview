package convert_bst_to_greater_tree;

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
    // 逆中序遍历，更新节点值为postfix sum
    // tag: binary tree
    // time: O(n)
    // space: O(n)
    private int sum;

    /*
     * @param root: the root of binary tree
     * @return: the new root
     */
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
