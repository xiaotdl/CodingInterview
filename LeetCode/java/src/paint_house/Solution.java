package paint_house;

/**
 * Created by Xiaotian on 12/23/16.
 */
class Solution {
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        int n = costs.length - 1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }
}

class SolutionII {
    // space can be improved to O(1) using rolling array, i%2
    // tag: dp
    // time: O(n)
    // space: O(n)
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;

        // dp[i][j]: minCost till i'th house with last house colored as j
        int[][] dp = new int[n][3];
        for (int j = 0; j < 3; j++) dp[0][j] = costs[0][j];
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.min(dp[n - 1][0],
               Math.min(dp[n - 1][1],
                        dp[n - 1][2]));
    }
}
