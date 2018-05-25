package two_sum_iv_input_is_bst;

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
    // tag: bfs, hash
    // time: O(n)
    // space: O(n)
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (set.contains(k - node.val)) return true;
            set.add(node.val);

            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
        return false;
    }
}
