package binary_tree_preorder_traversal_iterator;

import java.util.*;

/**
 * Created by Xiaotian on 4/5/18.
 */
class Solution {
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val) {
        this.val = val;
    }
    @Override
    public String toString(){
        return ""+val;
    }
}

class PreOrderIterator {
    // credit: http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-binarytree-ii.html

    private Stack<TreeNode> stack;

    public PreOrderIterator(TreeNode root) {
        stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode curr = stack.pop();
        if (curr.right != null) stack.push(curr.right);
        if (curr.left != null) stack.push(curr.left);

        return curr.val;
    }

    public static void main(String[] args) {
        //      1
        //    2   3
        //   4 5 6 7
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        TreeNode _6 = new TreeNode(6);
        TreeNode _7 = new TreeNode(7);
        _1.left = _2;
        _1.right = _3;
        _2.left = _4;
        _2.right = _5;
        _3.left = _6;
        _3.right = _7;

        PreOrderIterator it = new PreOrderIterator(_1);
        while (it.hasNext()) System.out.println(it.next());
    }
}
