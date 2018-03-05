package find_leaves_of_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: dfs, bottom up: pass height and add to res
    // time: O(n)
    // space: O(1)
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private int dfs(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;

        int lH = dfs(root.left, res);
        int rH = dfs(root.right, res);
        int currH = Math.max(lH, rH) + 1;

        if (currH >= res.size()) res.add(new ArrayList<Integer>());
        res.get(currH).add(root.val);
        return currH;
    }
}
