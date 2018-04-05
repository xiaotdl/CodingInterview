package binary_tree_postorder_traversal_iterator;

import java.util.*;

class Solution {}

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

class PostOrderIterator {
    // credit: http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-binarytree-iii.html

    private Stack<TreeNode> stack;

    public PostOrderIterator(TreeNode root) {
        stack = new Stack<>();
        findNextLeaf(root);
    }

    private void findNextLeaf(TreeNode curr) {
        while (curr != null) {
            stack.push(curr);
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode curr = stack.pop();
        if (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (curr == top.left) {
                findNextLeaf(top.right); // find next leaf in right sub-tree
            }
        }

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

        PostOrderIterator it = new PostOrderIterator(_1);
        while (it.hasNext()) System.out.println(it.next());
    }
}