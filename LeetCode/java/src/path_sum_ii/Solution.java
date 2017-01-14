package path_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 1/14/17.
 */
// tag: tree, dfs
// time: O(n)
// space: O(1)
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(TreeNode root, int sum, ArrayList<Integer> path, List<List<Integer>> res) {
        if (root.left == null && root.right == null) {
            path.add(root.val);
            int pathSum = sum(path);
            if (pathSum == sum) res.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        if (root.left != null) {
            path.add(root.val);
            dfs(root.left, sum, path, res);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            dfs(root.right, sum, path, res);
            path.remove(path.size() - 1);
        }
    }

    private int sum(ArrayList<Integer> arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }
}
