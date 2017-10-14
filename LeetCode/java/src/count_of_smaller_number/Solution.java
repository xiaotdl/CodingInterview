package count_of_smaller_number;

import java.util.*;

/**
 * Created by Xiaotian on 10/13/17.
 */
public class Solution {
    // tag: segment tree
    // time: O(n + k*logn + l*logn), n: 10000, k: len(A), l: len(queries)
    //   - build tree: O(n)
    //   - modify treenode: O(logn)
    //   - query treenode: O(logn)
    // space: O(n)
    class SegmentTreeNode {
        int start, end, count;
        SegmentTreeNode left, right;

        public SegmentTreeNode (int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode build(int l, int r) {
            if (l > r) return null;

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            if (l == r) return root;

            int m = l + (r - l) / 2;
            root.left = build(l, m);
            root.right = build(m + 1, r);
            return root;
        }

        public void modify(SegmentTreeNode root, int num) {
            if (num < root.start || num > root.end) return;

            if (num == root.start && num == root.end) {
                root.count++;
                return;
            }

            int mid = root.start + (root.end - root.start) / 2;
            if (num <= mid) {
                modify(root.left, num);
            } else {
                modify(root.right, num);
            }

            root.count = root.left.count + root.right.count;
        }

        public int query(SegmentTreeNode root, int l, int r) {
            if (root.end < l || root.start > r) return 0;

            if (l <= root.start && root.end <= r) return root.count;

            int mid = root.start + (root.end - root.start) / 2;
            return query(root.left, l, r) + query(root.right, l, r);
        }
    }
    /*
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        SegmentTree tree = new SegmentTree();
        SegmentTreeNode root = tree.build(0, 10000);
        for (int num : A) {
            tree.modify(root, num);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : queries) {
            res.add(tree.query(root, 0, num - 1));
        }
        return res;
    }
}
