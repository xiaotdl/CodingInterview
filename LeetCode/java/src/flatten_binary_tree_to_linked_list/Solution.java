package flatten_binary_tree_to_linked_list;

import java.util.*;

/**
 * Created by Xiaotian on 10/6/17.
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
    // tag: queue
    // time: O(n)
    // space: O(n)
    /*
     * @param root: a TreeNode, the root of the binary tree
     * @return:
     */
    public void flatten(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        preorder(root, q);

        TreeNode dummy = new TreeNode(0);
        TreeNode curr = dummy;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            node.left = null;
            curr.right = node;
            curr = curr.right;
        }
        root = dummy.right;
    }

    private void preorder(TreeNode root, Queue<TreeNode> q) {
        if (root == null) return;

        q.offer(root);
        preorder(root.left, q);
        preorder(root.right, q);
    }
}

class SolutionII {
    // tag: divide and conquer
    // time: O(n)
    // space: O(1)
    /*
     * @param root: a TreeNode, the root of the binary tree
     * @return:
     */
    public void flatten(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) return null;

        TreeNode leftTail = helper(root.left);
        TreeNode rightTail = helper(root.right);

        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightTail != null) return rightTail;
        if (leftTail != null) return leftTail;
        return root;
    }
}

class SolutionIII {
    // preorder traversal
    // tag: stack
    // time: O(n)
    // space: O(n)
    /*
     * @param root: a TreeNode, the root of the binary tree
     * @return:
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }

            // connect
            curr.left = null;
            curr.right = stack.isEmpty() ? null : stack.peek();
        }
    }
}
