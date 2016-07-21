package house_robber;

/**
 * Created by Xiaotian on 7/20/16.
 */
public class Solution {
    // tag: DP
    // time: O(n), one iteration through nums.
    // space: O(n), one dimensional additional space.
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from nums[0..i-1]
        int[] dp = new int[nums.length + 1];

        dp[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            // rob last house
            int a = nums[i - 1] + (i - 2 >= 0 ? dp[i - 2] : 0);
            // doesn't rob last house
            int b = dp[i - 1];
            dp[i] = Math.max(a, b);
        }

        return dp[nums.length];
    }
}
