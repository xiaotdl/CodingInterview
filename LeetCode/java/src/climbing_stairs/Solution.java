package climbing_stairs;

/**
 * Created by Xiaotian on 12/27/16.
 */
// tag: dp
// time: O(n)
// space: O(n)
public class Solution {
    public int climbStairs(int n) {
        if (n <= 0) return 0;
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
