package maximum_subarray;

/**
 * Created by Xiaotian on 12/28/16.
 */
// tag: dp
// time: O(n)
// space: O(n)
public class Solution {
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
