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

        return MemorySearch(values, dp, isVisited, 0, n - 1) > sum / 2;
    }

    private int MemorySearch(int[] values, int[][] dp, boolean[][] isVisited, int l, int r) {
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
                    MemorySearch(values, dp, isVisited, l + 2, r),     // other pick left
                    MemorySearch(values, dp, isVisited, l + 1, r - 1)  // other pick right
                ),
                // pick right
                values[r]
                + Math.min(
                    MemorySearch(values, dp, isVisited, l + 1, r - 1), // other pick left
                    MemorySearch(values, dp, isVisited, l, r - 2)      // other pick right
                )
            );
        }
        return dp[l][r];
    }
}
