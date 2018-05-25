package maximal_square;

/**
 * Created by Xiaotian on 12/29/16.
 */
class Solution {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2), space can be improved to O(n) thru 滚动优化
    public int maximalSquare(char[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;

        int m = M.length;
        int n = M[0].length;
        int max = 0; // max square side length
        // dp[i][j]: denotes maxSquare including point M[i][j] as bottom-right corner
        int[][] dp = new int[m][n];
        dp[0][0] = M[0][0] == '1' ? 1 : 0;
        max = Math.max(max, dp[0][0]);
        for (int i = 1; i < m; i++) {
            dp[i][0] = M[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = M[0][j] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (M[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],
                               Math.min(dp[i - 1][j],
                                        dp[i][j - 1]))
                               + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max*max;
    }
}
