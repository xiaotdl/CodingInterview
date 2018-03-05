package inorder_successor_in_bst;

import java.util.*;

/**
 * Created by Xiaotian on 1/31/18.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Solution {
    // 节点的successor 后继就是比给定节点大的所有节点中最小的那个
    // 参考二分法，考虑p和root之间的关系（p 为给定的节点，要找到p节点的后继）
    // 两种情况：
    //     1. root.val =< p.val 答案就在右子树中
    //     2. root.val >  p.val 答案有两个备选：
    //        a) 左子树中找 （如果找到就一定是它，因为左子树的中的元素都比根小）
    //        b) 就是root
    // tag: binary tree
    // time: O(n)
    // space: O(1)
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}

class SolutionII {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            if (node == p) {
                stack.push(p);
                break;
            }
            if (node.val > p.val) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = node.right;
            }
        }

        getNext(stack); // pop p
        return getNext(stack);
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
