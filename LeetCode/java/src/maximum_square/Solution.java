package maximum_square;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n^2)
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0; // max size
        // dp[i][j]: the size of max square including point at matrix[i][j]
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max*max;
    }
}
