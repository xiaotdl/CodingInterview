package kth_smallest_element_in_a_bst;

import java.util.*;

/**
 * Created by Xiaotian on 10/31/16.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: dfs, inorder
    // time: O(logn+k)
    // space: O(1)
    int result;
    int cnt;
    int k;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        this.k = k;
        inorderTraverse(root);
        return result;
    }

    void inorderTraverse(TreeNode root) {
        if (root == null || cnt >= this.k) {
            return;
        }
        inorderTraverse(root.left);
        cnt++;
        if (cnt == this.k) {
            result = root.val;
        }
        inorderTraverse(root.right);
    }
}


class SolutionII {
    // tag: bst iterator
    // time: O(logn+k)
    // space: O(1)
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) return -1;

        BSTIterator it = new BSTIterator(root);
        TreeNode node = null;
        while (k-- > 0) {
            node = it.next();
            if (node == null) return -1;
        }
        return node.val;
    }
}

class BSTIterator {
    Stack<TreeNode> stack;
    BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    TreeNode next() {
        if (stack.isEmpty()) return null;

        TreeNode curr = stack.pop();
        TreeNode next = curr.right;
        while (next != null) {
            stack.push(next);
            next = next.left;
        }
        return curr;
    }
}
