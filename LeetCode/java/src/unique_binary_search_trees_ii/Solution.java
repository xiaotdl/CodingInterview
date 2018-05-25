package unique_binary_search_trees_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/26/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    // tag: dp/dfs
    // time: O(n^3)
    // space: O(1)
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) {
            res.add(null);
            return res;
        }

        for (int i = l; i <= r; i++) { // i: root
            List<TreeNode> lTrees = helper(l, i - 1);
            List<TreeNode> rTrees = helper(i + 1, r);
            for (TreeNode lRoot : lTrees) {
                for (TreeNode rRoot : rTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = lRoot;
                    root.right = rRoot;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
