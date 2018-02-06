package maximum_subtree;

/**
 * Created by Xiaotian on 2/4/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    // tag: dfs
    // time: O(n)
    // space: O(1)
    /*
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public TreeNode findSubtree(TreeNode root) {
        if (root == null) return null;

        max = Integer.MIN_VALUE;
        maxNode = root;
        dfs(root);
        return maxNode;
    }

    int max;
    TreeNode maxNode;
    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int sum = root.val + dfs(root.left) + dfs(root.right);
        if (sum > max) {
            max = sum;
            maxNode = root;
        }
        return sum;
    }
}
