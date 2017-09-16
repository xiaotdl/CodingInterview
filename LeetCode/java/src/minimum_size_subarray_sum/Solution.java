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

class SolutionII {
    // Same as Solution
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int l, r;
        for (l = 0, r = 0; l < nums.length; l++) {
            while (r < nums.length && sum < s) {
                sum += nums[r];
                r++;
            }
            if (sum >= s) {
                min = Math.min(min, r - l);
            }
            sum -= nums[l];
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
