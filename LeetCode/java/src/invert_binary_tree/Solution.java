package invert_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 7/20/16.
 */
// tag: tree, dfs
// time: O(n)
// space: O(1)
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    // top-down
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
}

// tag: tree, stack
// time: O(n)
// space: O(n)
class SolutionII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // swap
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            // move on
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        return root;
    }
}

// tag: tree, queue, bfs
// time: O(n)
// space: O(n)
class SolutionIII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // swap
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            // move on
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return root;
    }
}
