package segment_tree_build_ii;

/**
 * Created by Xiaotian on 10/13/17.
 */
class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;
    }
}


public class Solution {
    // 自上而下递归分裂
    // 自下而上回溯更新
    // top-down build tree node
    // bottom-up update max value
    // tag: segment tree
    // time: O(n)
    // space: O(1)
    /*
     * @param A: a list of integer
     * @return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        return helper(A, 0, A.length - 1);
    }

    private SegmentTreeNode helper(int[] A, int l, int r) {
        if (l > r) return null;

        SegmentTreeNode root = new SegmentTreeNode(l, r, A[l]);
        if (l == r) return root;

        int m = l + (r - l) / 2;
        root.left = helper(A, l, m);
        root.right = helper(A, m + 1, r);
        root.max = Math.max(root.left.max, root.right.max);
        return root;
    }
}
