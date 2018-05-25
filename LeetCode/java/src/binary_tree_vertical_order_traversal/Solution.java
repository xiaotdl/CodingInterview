package binary_tree_vertical_order_traversal;

/**
 * Created by Xiaotian on 6/15/17.
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // - BFS, put node, col into queue at the same time
    // - Every left child access col - 1 while right child col + 1
    // - This maps node into different col buckets
    // - Get col boundary min and max on the fly
    // - Retrieve result from cols
    // tag: bfs, queue
    // time: O(n)
    // space: O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;

        Map<Integer, ArrayList<Integer>> col2nodes = new HashMap<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        int min = 0;
        int max = 0;

        nodes.add(root);
        cols.add(0);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int col = cols.poll();

            if (!col2nodes.containsKey(col)) {
                col2nodes.put(col, new ArrayList<Integer>());
            }
            col2nodes.get(col).add(node.val);

            if (node.left != null) {
                nodes.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                nodes.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int col = min; col <= max; col++) {
            results.add(col2nodes.get(col));
        }

        return results;
    }
}





