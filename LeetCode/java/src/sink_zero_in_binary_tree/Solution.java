package sink_zero_in_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 3/7/18.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BFS {
    static void traverse(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                System.out.print(curr.val + "  ");
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            System.out.println();
        }
    }
}

public class Solution {
    // Ref: https://www.geeksforgeeks.org/sink-odd-nodes-binary-tree/
    // tag: dfs
    // time: O(n^2)
    // space: O(h)
    public void sinkZeroes(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return; // isLeaf

        // post-order
        sinkZeroes(root.left);
        sinkZeroes(root.right);

        if (root.val == 0) sink(root);
    }

    private void sink(TreeNode node) {
        if (node.left != null) {
            swap(node, node.left);
            sink(node.left);
        }
        if (node.right != null) {
            swap(node, node.right);
            sink(node.right);
        }
    }

    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

    public static void main(String[] args) {
        TreeNode _1 = new TreeNode(0);
        TreeNode _2 = new TreeNode(0);
        TreeNode _3 = new TreeNode(0);
        TreeNode _4 = new TreeNode(0);
        TreeNode _5 = new TreeNode(1);
        TreeNode _6 = new TreeNode(4);
        TreeNode _7 = new TreeNode(5);
        _1.left = _2;
        _1.right = _3;
        _2.left = _4;
        _2.right = _5;
        _3.left = _6;
        _3.right = _7;
        new BFS().traverse(_1);
        new Solution().sinkZeroes(_1);
        new BFS().traverse(_1);
    }
}
