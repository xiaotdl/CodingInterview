package average_of_levels_in_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 5/5/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // tag: bfs
    // time: O(n)
    // space: O(n)
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            long lvlSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                lvlSum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(lvlSum / (double) size);
        }
        return res;
    }
}
