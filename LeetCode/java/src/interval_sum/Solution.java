package interval_sum;

import java.util.*;

/**
 * Created by Xiaotian on 10/13/17.
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Solution {
    // tag: segment tree
    // time: O(n + k*logn), n: len(A), k: len(queries)
    //   - build tree: O(n)
    //   - query treenode: O(logn)
    // space: O(n)
    class SegmentTreeNode {
        public int l, r;
        public long sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int l, int r, int sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode build(int[] A, int l, int r) {
            if (l > r) return null;

            SegmentTreeNode root = new SegmentTreeNode(l, r, A[l]);
            if (l == r) return root;

            int m = l + (r - l) / 2;
            root.left = build(A, l, m);
            root.right = build(A, m + 1, r);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        public long query(SegmentTreeNode root, int l, int r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.sum;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }

    /*
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        SegmentTree tree = new SegmentTree();
        SegmentTreeNode root = tree.build(A, 0, A.length - 1);
        List<Long> res = new ArrayList<>();
        for (Interval query : queries) {
            res.add(tree.query(root, query.start, query.end));
        }
        return res;
    }
}

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


class SolutionII {
    // tag: prefix sum
    // time:
    //   - build prefix sum: O(n)
    //   - query: O(1)
    // space: O(n)
    /*
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        long[] prefixSum = new long[A.length+1];
        prefixSum[0] = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }

        List<Long> res = new ArrayList<>();
        for (Interval query : queries) {
            res.add(prefixSum[query.end + 1] - prefixSum[query.start]);
        }
        return res;
    }
}
