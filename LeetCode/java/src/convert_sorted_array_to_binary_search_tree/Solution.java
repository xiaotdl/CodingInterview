package convert_sorted_array_to_binary_search_tree;

/**
 * Created by Xiaotian on 7/20/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: Tree, DFS
    // time: O(n), touched each tree node once.
    // space: O(logn), depth of the binary tree.
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) return null;

        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = dfs(nums, l, m - 1);
        root.right = dfs(nums, m + 1, r);
        return root;
    }
}
