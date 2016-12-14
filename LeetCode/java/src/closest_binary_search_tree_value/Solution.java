package closest_binary_search_tree_value;

/**
 * Created by Xiaotian on 12/14/16.
 */
// tag: binary search, iterative
// time: O(logn)
// space: O(1)
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            root = (root.val > target ? root.left : root.right);
        }
        return closest;
    }
}

// tag: binary search, recursive
// time: O(logn)
// space: O(1)
class SolutionII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = (target < a ? root.left : root.right);
        if (child == null) {
            return a;
        }
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}
