package maximum_subarray;

/**
 * Created by Xiaotian on 12/28/16.
 */
public class Solution {
    // Kadane's Algorithm: https://en.wikipedia.org/wiki/Maximum_subarray_problem
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max_ending_here = nums[0];
        int max_so_far = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max_ending_here = nums[i] + (max_ending_here > 0 ? max_ending_here : 0);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }

        return max_so_far;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n)
    // space: O(n)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp[i]: maxSubArray from nums[0..i] including nums[i]
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
