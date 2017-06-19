package range_sum_query_mutable;

/**
 * Created by Xiaotian on 6/18/17.
 */
public class Solution {
}
class NumArray {
    // credit: http://blog.csdn.net/l664675249/article/details/50157669
    // Binary Indexed Tree(BIT)
    // last bit: i & ((i - 1) ^ i)
    // tag: BIT
    // time:
    //   insert: O(logn)
    //   search: O(logn)
    // space: O(n)

    private int[] nums;
    private int[] tree;

    public NumArray(int[] nums) {
        this.nums = nums;
        int sum = 0;
        tree = new int[nums.length + 1];
        for (int i = 1; i < tree.length; i++) {
            for (int j = i; j > i - (i & ((i - 1) ^ i)); j--) {
                sum += nums[j - 1];
            }
            tree[i] = sum;
            sum = 0;
        }
    }

    public void update(int i, int val) {
        int tmp = nums[i];
        nums[i] = val;
        for (int j = i + 1 ;j < tree.length; j += j & ((j - 1) ^ j)) {
            tree[j] += val - tmp;
        }
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    public int getSum(int i) {
        int sum = 0;
        for (int j = i + 1; j > 0; j -= j & ((j - 1) ^ j)) {
            sum += tree[j];
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


class NumArrayII {
    // credit: https://discuss.leetcode.com/topic/29918/17-ms-java-solution-with-segment-tree
    // Segment Tree
    // tag: segment tree
    // time:
    //   build: O(n)
    //   insert: O(logn)
    //   search: O(logn)
    // space: O(n)

    private SegmentTreeNode root;
    private int[] nums;

    public NumArrayII(int[] nums) {
        this.nums = nums;
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        nums[i] = val;
        updateHelper(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRangeHelper(root, i, j);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0 || start > end) {
            return null;
        }
        if (start == end) {
            return new SegmentTreeNode(start, start, nums[start]);
        }

        SegmentTreeNode node = new SegmentTreeNode(start, end);
        int mid = start + (end - start) / 2;
        node.left = buildTree(nums, start, mid);
        node.right = buildTree(nums, mid + 1, end);
        node.sum = node.left.sum + node.right.sum;
        return node;
    }

    private void updateHelper(SegmentTreeNode root, int index, int val) {
        if (root.start == root.end) {
            root.sum = val;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            updateHelper(root.left, index, val);
        }
        else {
            updateHelper(root.right, index, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    private int sumRangeHelper(SegmentTreeNode root, int i, int j) {
        if (i > j || i < root.start || j > root.end) {
            return 0;
        }
        if (i == root.start & j == root.end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (j <= mid) {
            return sumRangeHelper(root.left, i, j);
        }
        else if (i >= mid + 1) {
            return sumRangeHelper(root.right, i, j);
        }
        else {
            return sumRangeHelper(root.left, i, mid)
                    + sumRangeHelper(root.right, mid + 1, j);
        }
    }

    class SegmentTreeNode {
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
