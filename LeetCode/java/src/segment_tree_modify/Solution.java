package segment_tree_modify;

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
    // 自上而下递归查找
    // 自下而上回溯更新
    // top-down search tree node
    // bottom-up update max value
    // tag: segment tree
    // time: O(logn)
    // space: O(1)
    /*
     * @param root: The root of segment tree.
     * @param index: index.
     * @param value: value
     * @return:
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        if (root == null || index < root.start || index > root.end) {
            return;
        }

        if (index == root.start && index == root.end) {
            root.max = value;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }
        root.max = Math.max(root.left.max, root.right.max);
    }
}
