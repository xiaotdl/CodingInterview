package construct_binary_tree_from_preorder_and_inorder_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 9/5/17.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: array, tree, dfs, recursion
    // time: O(n)
    // space: O(1)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> map) {
        if (preL > preR || inL > inR) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preL]);
        int i = map.get(root.val);

        root.left = helper(preorder, preL + 1, preL + i - inL, inorder, inL, i - 1, map);
        root.right = helper(preorder, preL + i - inL + 1, preR, inorder, i + 1, inR, map);
        return root;
    }
}
