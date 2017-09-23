package max_tree;

import java.util.*;

/**
 * Created by Xiaotian on 9/22/17.
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {
    // Cartesian Tree: https://en.wikipedia.org/wiki/Cartesian_tree
    // Cartesian Tree + Lowest Common Ancestor => range min/max query in an array
    // tag: stack
    // time: O(n)
    // space: O(n)
    /*
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) return null;

        Stack<TreeNode> stack = new Stack<>();
        for (int num : A) {
            TreeNode curr = new TreeNode(num);
            while (!stack.isEmpty() && curr.val > stack.peek().val) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }
        return root;
    }
}
