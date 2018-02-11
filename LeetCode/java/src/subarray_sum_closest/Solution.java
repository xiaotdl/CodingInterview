package subarray_sum_closest;

import java.util.*;

/**
 * Created by Xiaotian on 2/10/18.
 */
public class Solution {
    // tag: prefix sum, sort
    // time: O(nlogn)
    // space: O(n)
    class PrefixSum implements Comparable<PrefixSum> {
        int index; // index of A
        int val;
        public PrefixSum (int index, int val) {
            this.index = index;
            this.val = val;
        }

        public int compareTo(PrefixSum other) {
            return this.val - other.val;
        }
    }
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] A) {
        PrefixSum[] P = new PrefixSum[A.length + 1];
        int prefixSum = 0;
        P[0] = new PrefixSum(-1, prefixSum);
        for (int i = 1; i < A.length + 1; i++) {
            prefixSum = A[i - 1] + prefixSum;
            P[i] = new PrefixSum(i - 1, prefixSum);
        }

        Arrays.sort(P);

        int diff = 0;
        int minDiff = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 1; i < A.length + 1; i++) {
            diff = P[i].val - P[i-1].val;
            if (diff < minDiff) {
                minDiff = diff;
                start = Math.min(P[i - 1].index, P[i].index) + 1;
                end = Math.max(P[i - 1].index, P[i].index);
            }
        }
        return new int[]{start, end};
    }
}
