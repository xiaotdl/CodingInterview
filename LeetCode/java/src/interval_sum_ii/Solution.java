package interval_sum_ii;

/**
 * Created by Xiaotian on 10/13/17.
 */
public class Solution {
    // tag: segment tree
    // time:
    //   - build tree: O(n)
    //   - modify treenode: O(logn)
    //   - query treenode: O(logn)
    // space: O(n)
    class SegmentTreeNode {
        public int l, r;
        public long sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode (int l, int r, long sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode root;
        public SegmentTreeNode build(int[] A, int l, int r) {
            if (l > r) return null;

            if (l == r) return new SegmentTreeNode(l, r, A[l]);

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            int m = l + (r - l) / 2;
            root.left = build(A, l, m);
            root.right = build(A, m + 1, r);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        public void modify(SegmentTreeNode root, int i, int v) {
            if (i < root.l || i > root.r) return;

            if (i == root.l && i == root.r) {
                root.sum = v;
                return;
            }

            int m = root.l + (root.r - root.l) / 2;
            if (i <= m) {
                modify(root.left, i, v);
            } else {
                modify(root.right, i, v);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        public long query(SegmentTreeNode root, int l, int r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.sum;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }

    private SegmentTree tree;
    public Solution(int[] A) {
        tree = new SegmentTree();
        tree.root = tree.build(A, 0, A.length - 1);
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return tree.query(tree.root, start, end);
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        tree.modify(tree.root, index, value);
    }
}
