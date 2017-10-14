package interval_minimum_number;

import java.util.*;

/**
 * Created by Xiaotian on 10/13/17.
 */
class Interval{
    int start,end;
    Interval(int start, int end){
        this.start=start;
        this.end=end;
    }
}

public class Solution {
    // tag: segment tree
    // time: O(n + k*logn), n: len(A), k: len(queries)
    // space: O(n)
    class SegmentTreeNode {
        int start, end, min;
        SegmentTreeNode left, right;
        SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode build(int[] A, int l, int r) {
            if (A == null || l > r) return null;

            SegmentTreeNode root = new SegmentTreeNode(l, r, A[l]);
            if (l == r) return root;

            int m = l + (r - l) / 2;
            root.left = build(A, l, m);
            root.right = build(A, m + 1, r);
            root.min = Math.min(root.left.min, root.right.min);
            return root;
        }

        public int query(SegmentTreeNode root, int l, int r) {
            if (root.end < l || root.start > r) return Integer.MAX_VALUE;

            if (l <= root.start && root.end <= r) return root.min;

            return Math.min(query(root.left, l, r), query(root.right, l, r));
        }
    }
    /*
     * @param A: An integer array
     * @param queries: An query list
     * @return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        List<Integer> res = new ArrayList<>();
        SegmentTree tree = new SegmentTree();
        SegmentTreeNode root = tree.build(A, 0, A.length - 1);
        for (Interval query : queries) {
            res.add(tree.query(root, query.start, query.end));
        }
        return res;
    }
}
