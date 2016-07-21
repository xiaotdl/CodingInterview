package convert_sorted_array_to_binary_search_tree;

/**
 * Created by Xiaotian on 7/20/16.
 */
public class Solution {
    // tag: Tree, DFS
    // time: O(n), touched each tree node once.
    // space: O(logn), depth of the binary tree.
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        TreeNode root = helper(nums, 0, nums.length - 1);

        return root;
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - 1);
        root.right = helper(nums, mid + 1, end);

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
