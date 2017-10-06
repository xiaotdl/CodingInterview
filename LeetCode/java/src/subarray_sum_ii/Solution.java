package subarray_sum_ii;

/**
 * Created by Xiaotian on 10/5/17.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogn)
    // space: O(n)
    /*
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        // preSum[i]: sum(0..A[i - 1])
        int[] preSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            preSum[i] += A[i - 1] + preSum[i - 1]; // preSum is an increasing array
        }

        int cnt = 0;
        for (int i = 1; i < n + 1; i++) {
            // start <= preSum[i] - preSum[j] <= end
            //        A[j - 1] + ... + A[i - 1]
            // ==> l bound: preSum[j] >= preSum[i] - end
            // ==> r bound: preSum[j] <= preSum[i] - start
            int l = preSum[i] - end;
            int r = preSum[i] - start;
            cnt += binarySearchLastSmallerOrEq(preSum, r) - binarySearchFirstLargerOrEq(preSum, l) + 1;
        }
        return cnt;
    }

    private int binarySearchLastSmallerOrEq(int[] A, int target) {
        int l = 0;
        int r = A.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (A[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }
        if (A[r] <= target) return r;
        if (A[l] <= target) return l;
        return -1;
    }

    private int binarySearchFirstLargerOrEq(int[] A, int target) {
        int l = 0;
        int r = A.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (A[m] >= target) {
                r = m;
            } else {
                l = m;
            }
        }
        if (A[l] >= target) return l;
        if (A[r] >= target) return r;
        return -1;
    }
}
