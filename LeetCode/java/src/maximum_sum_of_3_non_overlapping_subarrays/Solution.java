package maximum_sum_of_3_non_overlapping_subarrays;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/
    // tag: array
    // time: O(n)
    // space: O(n)
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // sums[i]: sum from nums[i] to nums[i + k - 1]
        int[] sums = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        sums[0] = sum;
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k - 1];
        }

        int maxSumIdx;

        int[] lMaxSumIdx = new int[sums.length];
        maxSumIdx = 0;
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] > sums[maxSumIdx]) maxSumIdx = i;
            lMaxSumIdx[i] = maxSumIdx;
        }

        int[] rMaxSumIdx = new int[sums.length];
        maxSumIdx = sums.length - 1;
        for (int i = sums.length - 1; i >= 0; i--) {
            if (sums[i] >= sums[maxSumIdx]) maxSumIdx = i;
            rMaxSumIdx[i] = maxSumIdx;
        }

        int[] res = new int[]{-1, -1, -1};
        int maxSum = 0;
        for (int i = k; i < sums.length - k; i++) { // i: mid idx
            int l = lMaxSumIdx[i - k];
            int r = rMaxSumIdx[i + k];
            if (res[0] == -1
                    || sums[l] + sums[i] + sums[r] > maxSum) {
                maxSum = sums[l] + sums[i] + sums[r];
                res[0] = l; res[1] = i; res[2] = r;
            }
        }
        return res;
    }
}
