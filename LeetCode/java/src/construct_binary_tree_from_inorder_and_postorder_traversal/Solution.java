package construct_binary_tree_from_inorder_and_postorder_traversal;

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    private TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR, Map<Integer, Integer> map) {
        if (inL > inR || postL > postR) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postR]);
        int i = map.get(root.val);

        root.left = helper(inorder, inL, i - 1, postorder, postL, postL + i - 1 - inL, map);
        root.right = helper(inorder, i + 1, inR, postorder, postL + i - inL, postR - 1, map);
        return root;
    }
}
