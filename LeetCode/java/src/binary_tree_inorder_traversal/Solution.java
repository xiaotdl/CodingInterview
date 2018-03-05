package binary_tree_inorder_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 1/4/17.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// recursive
// tag: tree
// time: O(n)
// space: O(1)
public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}

// iterative
// tag: tree, stack
// time: O(n)
// space: O(n)
class SolutionII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

class SolutionIII {
    // iterative
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        TreeNode next = getNext(stack);
        while (next != null) {
            res.add(next.val);
            next = getNext(stack);
        }
        return res;
    }

    private TreeNode getNext(Stack<TreeNode> stack) {
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
