package binary_search_tree_iterator;

import java.util.Stack;

/**
 * Created by Xiaotian on 06/13/17.
 */
public class Solution {
    // inorder(left)   ^
    // root            | stack
    // inorder(right)
    // tag: stack
    // time: O(1)
    // space: O(h)
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class BSTIterator {
    // Same as Solution
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        traverseLeft(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        if (!hasNext()) return -1;
        TreeNode node = stack.pop();
        traverseLeft(node.right);
        return node.val;
    }

    private void traverseLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

class BSTIteratorII {
    // Same as Solution
    Stack<TreeNode> stack;
    public BSTIteratorII(TreeNode root) {
        stack = new Stack<>();
        TreeNode next = root;
        while (next != null) {
            stack.push(next);
            next = next.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        if (!hasNext()) return -1;
        TreeNode curr = stack.pop();
        TreeNode next = curr.right;
        while (next != null) {
            stack.push(next);
            next = next.left;
        }
        return curr.val;
    }
}
