package closest_binary_search_tree_value;

/**
 * Created by Xiaotian on 12/14/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
// tag: binary search, iterative
// time: O(logn)
// space: O(1)
public class Solution {

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

class SolutionIII {
    // Same as SolutionII
    // recursive
    public int closestValue(TreeNode root, double target) {
        if (root == null) return -1;
        return dfs(root, target);
    }

    private int dfs(TreeNode root, double target) {
        TreeNode child = root.val > target ? root.left : root.right;
        if (child == null) return root.val;
        int closestChild = dfs(child, target);
        return Math.abs(root.val - target) < Math.abs(closestChild - target) ? root.val : closestChild;
    }
}

class SolutionIV {
    // iterative
    // tag: binary search, iterative
    // time: O(logn)
    // space: O(1)
    public int closestValue(TreeNode root, double target) {
        int closestVal = root.val;
        while (root != null) {
            closestVal = (Math.abs(root.val - target) < Math.abs(closestVal - target) ? root.val : closestVal);
            if (closestVal == target) return closestVal;
            root = (root.val > target) ? root.left : root.right;
        }
        return closestVal;
    }
}
