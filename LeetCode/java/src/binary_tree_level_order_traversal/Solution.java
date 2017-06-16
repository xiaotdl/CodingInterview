package binary_tree_level_order_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 6/15/17.
 */
// tag: bfs
// time: O(n)
// space: O(b), b - max level size
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                level.add(node.val);

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }
            results.add(level);
        }

        return results;
    }
}

class SolutionII {
    // tag: dfs
    // time: O(n)
    // space: O(1)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        dfs(results, root, 0);
        return results;
    }

    public void dfs(List<List<Integer>> levels, TreeNode root, int level) {
        if (root == null) return;
        if (level >= levels.size()) {
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(root.val);
        dfs(levels, root.left, level + 1);
        dfs(levels, root.right, level + 1);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
