package unique_paths;

/**
 * Created by Xiaotian on 12/26/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(mn)
    // space: O(mn)
    public int uniquePaths(int m, int n) {
        // dp[i][j]: uniquePaths(i+1, j+1), uniquePaths from top-left to i-j grid
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

class SolutionII {
    // same as SolutionI, init outside for loop
    // tag: array, dp
    // time: O(mn)
    // space: O(mn)
    public int uniquePaths(int m, int n) {
        // dp[i][j]: uniquePaths from top-left to i-j grid
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

class SolutionIII {
    // dp[i][j] = dp[i-1][j] + dp[i][j-1]. Can save space by using dp[j] = dp[j-1] + dp[j].
    // tag: array, dp
    // time: O(mn)
    // space: O(n)
    public int uniquePaths(int m, int n) {
        // dp[j]: uniquePaths from top-left to i-j grid
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                }
                else {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}

class SolutionIV {
    // 滚动数组优化
    // tag: array, dp
    // time: O(mn)
    // space: O(n)
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] sum = new int[2][n];
        for (int i = 0; i < n; i++) {
            sum[0][i] = 1;
        }
        for (int j = 0; j < m; j++) {
            sum[j%2][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i%2][j] = sum[(i - 1)%2][j] + sum[i%2][j - 1];
            }
        }

        return sum[(m - 1)%2][n - 1];
    }
}


