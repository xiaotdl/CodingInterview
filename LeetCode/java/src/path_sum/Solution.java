package path_sum;

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

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, new ArrayList<Integer>(), sum);
    }

    private boolean dfs(TreeNode root, ArrayList<Integer> path, int sum) {
        if (root.left == null && root.right == null) {
            path.add(root.val);
            int pathSum = sum(path);
            path.remove(path.size() - 1);
            return pathSum == sum;
        }
        boolean left = false;
        boolean right = false;
        if (root.left != null) {
            path.add(root.val);
            left = dfs(root.left, path, sum);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            right = dfs(root.right, path, sum);
            path.remove(path.size() - 1);
        }
        return left || right;
    }

    private int sum(ArrayList<Integer> arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }
}

// tag: tree, dfs
// time: O(n)
// space: O(1)
class SolutionII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}
