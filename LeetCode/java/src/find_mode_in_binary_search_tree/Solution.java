package find_mode_in_binary_search_tree;

import java.util.*;

/**
 * Created by Xiaotian on 6/16/17.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(n)
    int maxCnt;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        Map<Integer, Integer> m = new HashMap<>(); //val2cnt
        inorder(root, m);
        List<Integer> list = new ArrayList<>();
        for (Integer val : m.keySet()) {
            int cnt = m.get(val);
            System.out.println(cnt);
            if (cnt == maxCnt) {
                list.add(val);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private void inorder(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) return;
        inorder(node.left, map);
        int cnt = map.containsKey(node.val) ? map.get(node.val) + 1 : 1;
        map.put(node.val, cnt);
        maxCnt = Math.max(maxCnt, cnt);
        inorder(node.right, map);
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
