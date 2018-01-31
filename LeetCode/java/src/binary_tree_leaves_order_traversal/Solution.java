package binary_tree_leaves_order_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 2/1/18.
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
    // dfs计算节点高度, hash保存答案
    // tag: binary tree
    // time: O(n)
    // space: O(n)
    Map<Integer, List<Integer>> map = new HashMap<>(); //depth2nodeVals
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        int maxDepth = dfs(root);
        for (int i = 1; i <= maxDepth; i++) {
            res.add(map.get(i));
        }

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = Math.max(dfs(root.left), dfs(root.right)) + 1;
        map.putIfAbsent(depth, new ArrayList<Integer>());
        map.get(depth).add(root.val);
        return depth;
    }
}
