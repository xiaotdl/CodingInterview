package count_univalue_subtree;

/**
 * Created by Xiaotian on 2/19/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // bottom up
    // tag: dfs
    // time: O(n)
    // space: O(1)
    int cnt;
    public int countUnivalSubtrees(TreeNode root) {
        cnt = 0;
        dfs(root);
        return cnt;
    }

    // dfs, returns num if isUnivalSubtree, else Integer.MIN_VALUE
    private int dfs(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        int l = dfs(root.left);
        int r = dfs(root.right);

        if (root.left != null && root.right != null) {
            if (root.val == l && root.val == r) {
                cnt++;
                return root.val;
            }
            else return Integer.MIN_VALUE;
        }
        else if (root.left != null) {
            if (root.val == l) {
                cnt++;
                return root.val;
            }
            else return Integer.MIN_VALUE;
        }
        else if (root.right != null) {
            if (root.val == r) {
                cnt++;
                return root.val;
            }
            else return Integer.MIN_VALUE;
        }
        else {
            cnt++;
            return root.val;
        }
    }
}
