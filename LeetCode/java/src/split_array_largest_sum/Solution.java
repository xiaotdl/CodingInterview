package split_array_largest_sum;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/split-array-largest-sum/solution/
    // select m cut from nums.length - 1
    // Given a result, it is easy to test whether it is valid or not.
    // The max of the result is the sum of the input nums.
    // The min of the result is the max num of the input nums.
    // Given the 3 conditions above we can do a binary search. (need to deal with overflow)

    // tag: binary search, greedy
    // time: O(n * log(sum(nums)))
    // space: O(1)
    public int splitArray(int[] nums, int m) {
        long sum = 0;
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            sum += num;
        }
        return (int) bsMinSum(nums, m, maxNum, sum);
    }

    private long bsMinSum(int[] nums, int groupCnt, long minGroupSum, long maxGroupSum) {
        long l = minGroupSum;
        long r = maxGroupSum;
        while (l + 1 < r) {
            long m = l + (r - l) / 2;
            if (valid(nums, groupCnt, m)) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (valid(nums, groupCnt, l)) return l;
        if (valid(nums, groupCnt, r)) return r;
        return -1;
    }

    // greedy
    private boolean valid(int[] nums, int groupCnt, long maxGroupSum) {
        int currGroupCnt = 0;
        for (int i = 0, j = 0; i < nums.length; i = j + 1) { // i: startIdx, j: endIdx
            int sum = nums[i];
            j = i;
            while (j + 1 < nums.length && sum + nums[j + 1] <= maxGroupSum) {
                sum += nums[j + 1];
                j++;
            }
            currGroupCnt++;
            if (currGroupCnt > groupCnt) return false;
        }
        return true;
    }
}
