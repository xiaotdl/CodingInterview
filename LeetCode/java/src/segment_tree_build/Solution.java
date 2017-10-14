package segment_tree_build;

/**
 * Created by Xiaotian on 10/12/17.
 */
class SegmentTreeNode {
    public int start, end;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end) {
        this.start = start; this.end = end;
        this.left = this.right = null;
    }
}


public class Solution {
    // top-down
    // tag: segment tree
    // time: O(n)
    // space: O(1)
    /*
     * @param start: start value.
     * @param end: end value.
     * @return: The root of Segment Tree.
     */
    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null;

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) return root;

        int mid = start + (end - start) / 2;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }
}
