package binary_tree_zigzag_level_order_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 4/22/18.
 */

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val) {
        this.val = val;
        left=right=null;
    }
}
class Solution {
    // tag: stack, bfs
    // time: O(n)
    // space: O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> lrStack = new Stack<>();
        Stack<TreeNode> rlStack = new Stack<>();
        lrStack.push(root);
        int levelNum = 1;
        while (!lrStack.isEmpty() || !rlStack.isEmpty()) {
            List<Integer> currLevel = new ArrayList<>();
            if (levelNum % 2 == 1) {
                int size = lrStack.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = lrStack.pop();
                    currLevel.add(curr.val);
                    if (curr.left != null) rlStack.push(curr.left);
                    if (curr.right != null) rlStack.push(curr.right);
                }
            }
            else {
                int size = rlStack.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = rlStack.pop();
                    currLevel.add(curr.val);
                    if (curr.right != null) lrStack.push(curr.right);
                    if (curr.left != null) lrStack.push(curr.left);
                }
            }
            levelNum++;
            res.add(currLevel);
        }
        return res;
    }
}
