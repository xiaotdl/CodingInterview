package minimum_size_subarray_sum;

/**
 * Created by Xiaotian on 6/15/17.
 */
public class Solution {
    // sliding window
    // tag: array, ptr
    // time: O(n)
    // space: O(1)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int l = 0;
        int r = 0;
        int currSum = 0;
        int minWinLen = Integer.MAX_VALUE;
        while (r < nums.length) {
            currSum += nums[r];
            while (l <= r && currSum >= s) {
                minWinLen = Math.min(minWinLen, r - l + 1);
                currSum -= nums[l];
                l++;
            }
            r++;
        }

        return minWinLen == Integer.MAX_VALUE ? 0 : minWinLen;
    }
}
