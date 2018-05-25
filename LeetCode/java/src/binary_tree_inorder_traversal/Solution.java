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

class SolutionIV {
    // credit: https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
    // morris inorder traversal: inorder traversal without recursion, stack or additional space
    // tag: tree
    // time: O(n)
    // space: O(1)
    public void morrisTraverse(TreeNode root) {
        if (root == null) return;

        TreeNode curr, prev;
        curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
            else {
                /* Find the inorder predecessor of current */
                prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                /* Make current as right child of its inorder predecessor */
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                }

                 /* Revert the changes made in if part to restore the
                    original tree i.e.,fix the right child of predecssor*/
                else {
                    prev.right = null;
                    System.out.print(curr.val + " ");
                    curr = curr.right;
                }   /* End of if condition prev->right == NULL */

            } /* End of if condition current->left == NULL*/

        } /* End of while */
    }

    public static void main(String[] args) {
        /* Constructed binary tree is
                   1
                 /  \
                2    3
              /  \
             4   5
        */
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        _1.left = _2;
        _1.right = _3;
        _2.left = _4;
        _2.right = _5;

        new SolutionIV().morrisTraverse(_1);
    }
}
