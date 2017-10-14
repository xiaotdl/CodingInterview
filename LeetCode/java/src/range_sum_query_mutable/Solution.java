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
    // tag: segment tree
    // time:
    //   build: O(n)
    //   insert: O(logn)
    //   search: O(logn)
    // space: O(n)
    class SegmentTreeNode {
        public int l, r, sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int l, int r, int sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            left = right = null;
        }
    }

    class SegmentTree {
        public SegmentTreeNode root;

        public SegmentTreeNode build(int[] nums, int l, int r) {
            if (l > r) return null;

            if (l == r) return new SegmentTreeNode(l, r, nums[l]);

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            int m = l + (r - l) / 2;
            root.left = build(nums, l, m);
            root.right = build(nums, m + 1, r);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        public void modify(SegmentTreeNode root, int i, int val) {
            if (root.r < i || root.l > i) return;

            if (i == root.l && i == root.r) {
                root.sum = val;
                return;
            }

            int m = root.l + (root.r - root.l) / 2;
            if (i <= m) {
                modify(root.left, i, val);
            } else {
                modify(root.right, i, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        public int query(SegmentTreeNode root, int l, int r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.sum;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }

    SegmentTree tree;
    public NumArrayII(int[] nums) {
        tree = new SegmentTree();
        tree.root = tree.build(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        tree.modify(tree.root, i, val);
    }

    public int sumRange(int i, int j) {
        return tree.query(tree.root, i, j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

