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

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushNodeAndLeftToStackRecursively(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.pop();
        pushNodeAndLeftToStackRecursively(curr.right);
        return curr.val;
    }

    private void pushNodeAndLeftToStackRecursively(TreeNode node) {
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
