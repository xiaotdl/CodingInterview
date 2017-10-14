package segment_tree_query;

/**
 * Created by Xiaotian on 10/12/17.
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
    // collect all sub range node results that is within start and end
    // ignore out of range, dig into overlap range, collect within range
    // tag: segment tree
    // time: O(logn)
    // space: O(1)
    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root.end < start || root.start > end) { // root doesn't overlap with query, stop traversing root
            return Integer.MIN_VALUE;
        }

        if (start <= root.start && root.end <= end) { // root equals/inside query, return root result
            return root.max;
        }

        return Math.max(query(root.left, start, end), // root overlap with query, recursively traverse root
                        query(root.right, start, end));
    }
}
