package longest_univalue_path;

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
    // tag: dfs
    // time: O(n)
    // space: O(1)
    int maxCnt;
    public int longestUnivaluePath(TreeNode root) {
        maxCnt = 0;
        dfs(root);
        return maxCnt == 0 ? 0 : maxCnt - 1;
    }

    // global maxCnt
    // dfs returns (cnt)
    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int cntL = dfs(root.left);
        int cntR = dfs(root.right);

        int cnt = 1; // max(cntL, cntR) + 1
        int localMaxCnt = 1; // cntL + 1 + cntR
        if (root.left != null && root.val == root.left.val) {
            cnt = Math.max(cnt, 1 + cntL);
            localMaxCnt += cntL;
        }
        if (root.right != null && root.val == root.right.val) {
            cnt = Math.max(cnt, 1 + cntR);
            localMaxCnt += cntR;
        }
        maxCnt = Math.max(maxCnt, localMaxCnt);
        return cnt;
    }
}
