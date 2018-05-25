package coin_change_ii;

/**
 * Created by Xiaotian on 4/22/18.
 */
class Solution {
    // tag: dp
    // time: O(m*n)
    // space: O(m*n)
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int m = amount;
        // dp[i][j]: ways to pick a few from first i coins that sum to j
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) dp[i][0] = 1;
        for (int j = 1; j < m + 1; j++) dp[0][j] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j] + (j - coins[i - 1] >= 0 ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[n][m];
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
    // Now we can see that dp[i][j] only rely on dp[i-1][j] and dp[i][j-coins[i]], then we can optimize the space by only using one-dimension array.
    // tag: dp
    // time: O(m*n)
    // space: O(m)
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int m = amount;
        // dp[i]: ways to pick a few coins that sum to i
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m + 1; i++) {
                dp[i] += (i - coins[j] >= 0 ? dp[i - coins[j]] : 0);
            }
        }
        return dp[m];
    }
}

