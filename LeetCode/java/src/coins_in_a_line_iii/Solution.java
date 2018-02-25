package coins_in_a_line_iii;

/**
 * Created by Xiaotian on 10/2/17.
 */
public class Solution {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    /*
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        // dp[i][j]: max money first can get from values[i..j]
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] isVisited = new boolean[n + 1][n + 1];

        int sum = 0;
        for (int v : values) {
            sum += v;
        }

        return memSearch(values, dp, isVisited, 0, n - 1) > sum / 2;
    }

    private int memSearch(int[] values, int[][] dp, boolean[][] isVisited, int l, int r) {
        if (isVisited[l][r]) {
            return dp[l][r];
        }
        isVisited[l][r] = true;

        if (l > r) {
            return dp[l][r];
        } else if (l == r) {
            dp[l][r] = values[l];
        } else if (l + 1 == r) {
            dp[l][r] = Math.max(values[l], values[r]);
        } else {
            dp[l][r] = Math.max(
                // pick left
                values[l]
                + Math.min(
                    memSearch(values, dp, isVisited, l + 2, r),     // other pick left
                    memSearch(values, dp, isVisited, l + 1, r - 1)  // other pick right
                ),
                // pick right
                values[r]
                + Math.min(
                    memSearch(values, dp, isVisited, l + 1, r - 1), // other pick left
                    memSearch(values, dp, isVisited, l, r - 2)      // other pick right
                )
            );
        }
        return dp[l][r];
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    /*
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] V) {
        int n = V.length;
        // sum[i]: sum of V[0..i-1]
        int[] sum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + V[i - 1];
        }

        // dp[i][j]: max coin value the first hand player can get from V[i..j]
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = V[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int j = i + len - 1;
                if (j >= n) continue;
                dp[i][j] = Math.max(
                        sum[j + 1] - sum[i] - dp[i + 1][j],
                        sum[j + 1] - sum[i] - dp[i][j - 1]
                );
            }
        }
        return dp[0][n - 1] > sum[n]/2;
    }
}
