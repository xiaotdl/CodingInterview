package reverse_pairs;

/**
 * Created by Xiaotian on 10/14/17.
 */
class Solution {
    // XXX: Wrong answer!!! Can't handle negative case.
    // Input:
    // [-5,-5]
    // Output:
    // 0
    // Expected:
    // 1
    // tag: segment tree
    class SegmentTreeNode {
        public long l, r;
        public int count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(long l, long r, int count) {
            this.l = l;
            this.r = r;
            this.count = count;
        }
    }

    class SegmentTree {
        public SegmentTreeNode root;

        public SegmentTree(long l, long r) {
            this.root = build(l, r);
        }

        public SegmentTreeNode build(long l, long r) {
            if (l > r) return null;

            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            if (l == r) return root;

            long m = l + (r - l) / 2;
            root.left = build(l, m);
            root.right = build(m + 1, r);
            return root;
        }

        public void modify(SegmentTreeNode root, long num) {
            if (num < root.l || num > root.r) return;

            if (num == root.l && num == root.r) {
                root.count++;
                return;
            }

            long m = root.l + (root.r - root.l) / 2;
            if (num <= m) {
                modify(root.left, num);
            } else {
                modify(root.right, num);
            }
            root.count = root.left.count + root.right.count;
        }

        public int query(SegmentTreeNode root, long l, long r) {
            if (root.r < l || root.l > r) return 0;

            if (l <= root.l && root.r <= r) return root.count;

            return query(root.left, l, r) + query(root.right, l, r);
        }
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        long diff = min < 0 ? -min : 0;

        SegmentTree tree = new SegmentTree(min + diff, max + diff);

        int cnt = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            cnt += tree.query(tree.root, diff, (nums[i] - 1) / 2 + diff);
            tree.modify(tree.root, nums[i] + diff);
        }
        return cnt;
    }
}

class SolutionII {
// I -- Sequential recurrence relation
// T(i, j) = T(i, j - 1) + C
// T(i, j) denotes the total number of important reverse pairs for subarray nums[i, j]
// T(0, j) = T(0, j - 1) + C
// where the subproblem C now becomes "find the number of important reverse pairs with the first element of the pair coming from subarray nums[0, j - 1] while the second element of the pair being nums[j]".
// important reverse pair (p, q) conditions:
// 1. p < q
// 2. nums[p] > 2 * nums[q]
// The straightforward way of searching: a linear scan of the subarray, which runs at the order of O(j), which leads to the naive O(n^2) solution.
// To improve the searching efficiency, we find the order doesn't matter, so we can do a sort and binary search the element instead of a plain linear scan.
// If the searching space is **static**, we just need a one-time sort and then concern about searching alone.
// However in this case, the searching space is expanding as we insert more elements.
// Considering the balance of searching and inserting operations, Binary Search Tree(BST) or Binary Indexed Tree(BIT) offers relatively fast performance for both operation.
// This homemade BST is not self-balanced and the time complexity can go as bad as O(n^2). To guarantee O(nlogn) performance, use one of the self-balanced BST's (e.g. Red-black tree, AVL tree, etc.).

    // TLE for worst case
    // tag: binary search tree
    // time: O(nlogn), worst case O(n^2)
    // space: O(n)
    class Node {
        int val, cnt;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.cnt = 1; // cnt is the total number of nodes in the subtree which rooted at current node that are greater than or equal to node.val, in another words, how many nodes greater than or equal to itself on the right side; left node cnts will be calculated when doing searching.
        }
    }

    class BST {

        public int search(Node root, long val) {
            if (root == null) return 0;

            if (val == root.val) {
                return root.cnt;
            } else if (val < root.val) {
                return root.cnt + search(root.left, val);
            } else {
                return search(root.right, val);
            }
        }

        public Node insert(Node root, int val) {
            if (root == null) return new Node(val);

            if (val == root.val) {
                root.cnt++;
            } else if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.cnt++;
                root.right = insert(root.right, val);
            }
            return root;
        }
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        BST tree = new BST();
        int res = 0;
        Node root = null;
        for (int num : nums) {
            res += tree.search(root, 2L * num + 1);
            root = tree.insert(root, num);
        }

        return res;
    }
}

class SolutionIII {
    // https://discuss.leetcode.com/topic/79227/general-principles-behind-problems-similar-to-reverse-pairs/2
    // tag: binary index tree
}

class SolutionIV {
    // tag: brutal force
    // time: O(n^2)
    // space: O(1)
    /*
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] A) {
        long res = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int cntSmallerNumsRHS = 0;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i]) cntSmallerNumsRHS++;
            }
            res += cntSmallerNumsRHS;
        }
        return res;
    }
}

class SolutionV {
// II -- Partition recurrence relation
// i = 0, j = n - 1, m = (n-1)/2
// T(0, n - 1) = T(0, m) + T(m + 1, n - 1) + C
// where the subproblem C now reads "find the number of important reverse pairs with the first element of the pair coming from the left subarray nums[0, m] while the second element of the pair coming from the right subarray nums[m + 1, n - 1]".

    // tag: merge sort
    // time: O(nlog(n))
    //   In each step we divide the array into 2 sub-arrays, and hence, the maximum times we need to divide is equal to O(log(n))O(log(n))
    //   Additional O(n) work needs to be done to count the inversions and to merge the 2 sub-arrays after sorting. Hence total time complexity is O(n * log(n))O(n∗log(n))
    // space: O(n)
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) return 0;

        int m = l + (r - l) / 2;
        int cnt = mergeSort(nums, l, m) + mergeSort(nums, m + 1, r);
        // calculate important reverse pairs before merge
        for (int i = l, j = m + 1; i <= m; i++) {
            while (j <= r && nums[i] > nums[j] * 2L) {
                j++;
            }
            cnt += j - (m + 1);
        }
        merge(nums, l, m, r);
        return cnt;
    }

    private void merge(int[] nums, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = nums[l + i];
        for (int j = 0; j < n2; j++) R[j] = nums[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k++] = L[i++];
            } else {
                nums[k++] = R[j++];
            }
        }
        while (i < n1) nums[k++] = L[i++];
        while (j < n2) nums[k++] = R[j++];
    }
}
