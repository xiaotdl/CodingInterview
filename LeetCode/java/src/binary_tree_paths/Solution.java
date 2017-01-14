package binary_tree_paths;

import java.util.*;

/**
 * Created by Xiaotian on 1/13/17.
 */
// tag: tree, dfs
// time: O(n)
// space: O(1)
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs (TreeNode root, ArrayList<Integer> tmpRes, List<String> res) {
        if (root.left == null && root.right == null) {
            tmpRes.add(root.val);
            res.add(join(tmpRes, "->"));
            tmpRes.remove(tmpRes.size() - 1);
            return;
        }
        if (root.left != null) {
            tmpRes.add(root.val);
            dfs(root.left, tmpRes, res);
            tmpRes.remove(tmpRes.size() - 1);
        }
        if (root.right != null) {
            tmpRes.add(root.val);
            dfs(root.right, tmpRes, res);
            tmpRes.remove(tmpRes.size() - 1);
        }
    }

    private String join(ArrayList arr, String delimiter) {
        String s = "";
        for (int i = 0; i < arr.size(); i++) {
            if (i != arr.size() - 1) {
                s += arr.get(i) + delimiter;
            }
            else {
                s += arr.get(i);
            }
        }
        return s;
    }
}
