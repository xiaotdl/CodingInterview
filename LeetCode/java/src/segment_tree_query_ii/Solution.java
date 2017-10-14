package segment_tree_query_ii;

/**
 * Created by Xiaotian on 10/12/17.
 */
class SegmentTreeNode {
    public int start, end, count;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.left = this.right = null;
    }
}

public class Solution {
    // tag: segment tree
    // time: O(logn)
    // space: O(1)
    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null) return 0;

        if (root.end < start || root.start > end) {
            return 0;
        }

        if (start <= root.start && root.end <= end) {
            return root.count;
        }

        return query(root.left, start, end) + query(root.right, start, end);
    }
}
