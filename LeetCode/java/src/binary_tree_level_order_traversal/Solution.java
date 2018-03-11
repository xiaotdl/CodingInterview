package binary_tree_level_order_traversal;

import java.util.*;

/**
 * Created by Xiaotian on 6/15/17.
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

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

class SolutionIII {
/**
 * Sample input:
 *
 *          1
 *         / \
 *        2   3
 *       / \  / \
 *      4   5 6  7
 *
 * Expected output:
 *    1
 *    2 3
 *    4 5 6 7
 *
 * Follow up expected output:
 *          1
 *        2   3
 *      4  5 6  7
 **/
    // 思路跟把整个binary tree inorder存到array里很像。
    // 先dfs求树的高度h。
    // 然后把每层都存到大小为2^h - 1的array里，逐层打出来就好了。
    // 用一个map来存每个节点的左右边界l, r，这样当前节点的array index就是(l+r)/2
    public void levelOrderPrint(TreeNode root) {
        int h = dfs(root);
        int totalNodes = (1 << h) - 1;

        Map<TreeNode, int[]> m = new HashMap<>(); // node2(l,r)

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        m.put(root, new int[]{0, totalNodes - 1});
        int currH = h;
        while (!q.isEmpty()) {
            int size = q.size();
            int[] lvl = new int[totalNodes];
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                int currPos = (m.get(curr)[1] + m.get(curr)[0]) / 2;
                lvl[currPos] = curr.val;
                if (curr.left != null) {
                    q.offer(curr.left);
                    m.put(curr.left, new int[]{m.get(curr)[0], currPos - 1});
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                    m.put(curr.right, new int[]{currPos + 1, m.get(curr)[1]});
                }
            }
            currH--;
            for (int i = 0; i < lvl.length; i++) {
                System.out.print(lvl[i] != 0 ? lvl[i] : " ");
            }
            System.out.println();
        }
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        return Math.max(dfs(root.left), dfs(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        _1.left = _2;
        _1.right = _3;
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        _2.left = _4;
        _2.right = _5;
        TreeNode _6 = new TreeNode(6);
        TreeNode _7 = new TreeNode(7);
        _3.left = _6;
        _3.right = _7;
        new SolutionIII().levelOrderPrint(_1);
    }

}
