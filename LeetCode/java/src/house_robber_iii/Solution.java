package house_robber_iii;

/**
 * Created by xili on 7/21/16.
 */
// tag: DFS
// time: O(n)
// space: O(1), no additional space
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(root.val + val, rob(root.left) + rob(root.right));
    }
}

// same as SolutionI
class SolutionII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int rob(TreeNode root) {
        if (root == null) return 0;
        // money1: rob root
        // money2: doesn't rob root
        int money1 = root.val;
        if (root.left != null) {
            money1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money1 += rob(root.right.left) + rob(root.right.right);
        }
        int money2 = rob(root.left) + rob(root.right);
        return Math.max(money1, money2);
    }
}

// saves both rob/not rob value, thus much faster than SolutionI
// tag: dp
// time: O(n)
// space: O(1), no additional space
class SolutionIII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    int[] helper(TreeNode root) {
        // r[0]: doesn't rob root; r[1]: rob root
        int[] res = new int[2];
        if (root == null) return res;
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
