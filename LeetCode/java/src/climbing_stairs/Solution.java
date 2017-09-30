package climbing_stairs;

/**
 * Created by Xiaotian on 12/27/16.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(n), space can be improved to O(n) thru 滚动优化
    public int climbStairs(int n) {
        if (n <= 2) return n;
        // dp[i]: distinct ways from stairs[1..i]
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
