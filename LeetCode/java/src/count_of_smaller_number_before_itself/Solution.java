package count_of_smaller_number_before_itself;

import java.util.*;

/**
 * Created by Xiaotian on 10/14/17.
 */
public class Solution {
    // tag: segment tree
    // time:
    //   - build tree: O(n)
    //   - modify treenode: O(logn)
    //   - query treenode: O(logn)
    // space: O(n)
    class SegmentTreeNode {
        public int l, r, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int l, int r, int count) {
            this.l = l;
            this.r = r;
            this.count = count;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode build(int l, int r) {
            if (l > r) return null;

            if (l == r) return new SegmentTreeNode(l, r, 0);

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            int m = l + (r - l) / 2;
            root.left = build(l, m);
            root.right = build(m + 1, r);
            return root;
        }

        public void modify(SegmentTreeNode root, int num) {
            if (num < root.l || num > root.r) return;

            if (num == root.l && num == root.r) {
                root.count++;
                return;
            }

            int m = root.l + (root.r - root.l) / 2;
            if (num <= m) {
                modify(root.left, num);
            } else {
                modify(root.right, num);
            }
            root.count = root.left.count + root.right.count;
        }

        public int query(SegmentTreeNode root, int l, int r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.count;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }
    /*
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        SegmentTree tree = new SegmentTree();
        SegmentTreeNode root = tree.build(0, 10000);

        List<Integer> res = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            res.add(tree.query(root, 0, A[i] - 1));
            tree.modify(root, A[i]);
        }
        return res;
    }
}
