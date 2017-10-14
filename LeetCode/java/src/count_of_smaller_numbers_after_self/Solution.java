package count_of_smaller_numbers_after_self;

import java.util.*;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogn)
    // space: O(n)
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> tmp = new ArrayList<>(n);
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) res.add(0);

        for (int i = n - 1; i >= 0; i--) {
            // find first smaller num than nums[i] in tmp, then insert nums[i] into smaller_index+1
            int first_smaller = binary_search_first_smaller(tmp, nums[i]);
            res.set(i, first_smaller + 1);
            tmp.add(first_smaller + 1, nums[i]);
        }

        return res;
    }

    private int binary_search_first_smaller(List<Integer> list, int target) {
        if (list == null || list.size() == 0) return -1;

        int l = 0;
        int r = list.size() - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (list.get(m) >= target) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (list.get(r) < target) {
            return r;
        }
        else if (list.get(l) < target) {
            return l;
        }
        return -1;
    }
}

class SolutionII {
    // tag: segment tree
    // time:
    //   build: O(n)
    //   modify: O(logn)
    //   query: O(logn)
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
        public SegmentTreeNode root;

        public SegmentTree(int l, int r) {
            this.root = build(l, r);
        }

        public SegmentTreeNode build(int l, int r) {
            if (l > r) return null;

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            if (l == r) return root;

            int m = l + (r - l) / 2;
            root.left = build(l, m);
            root.right = build(m + 1, r);
            return root;
        }

        public void modify(SegmentTreeNode root, int v) {
            if (v < root.l || v > root.r) return ;

            if (v == root.l && v == root.r) {
                root.count++;
                return;
            }

            int m = root.l + (root.r - root.l) / 2;
            if (v <= m) {
                modify(root.left, v);
            } else {
                modify(root.right, v);
            }
            root.count = root.left.count + root.right.count;
        }

        public int query(SegmentTreeNode root, int l, int r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.count;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;

        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // map nums to positive nums, value would be used as range index which is positive
        int diff = min < 0 ? -min : 0;

        SegmentTree tree = new SegmentTree(min + diff, max + diff);
        for (int i = nums.length - 1; i >= 0; i--) {
            res.add(0, tree.query(tree.root, 0, nums[i] - 1 + diff));
            tree.modify(tree.root, nums[i] + diff);
        }
        return res;
    }
}
