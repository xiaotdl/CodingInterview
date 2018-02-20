package longest_line_of_consecutive_ones_in_a_matrix;

/**
 * Created by Xiaotian on 2/18/18.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int m = M.length;
        int n = M[0].length;
        // dp[i][j][k]: from (0,0) to (i,j), k is longestLine of horizontal, vertical, diagonal and anti-diagonal
        int[][][] dp = new int[m][n][4];
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j + 1 < n) ? dp[i - 1][j + 1][3] + 1: 1;
                    maxLen = Math.max(maxLen,
                             Math.max(dp[i][j][0],
                             Math.max(dp[i][j][1],
                             Math.max(dp[i][j][2],
                                      dp[i][j][3]))));
                }
            }
        }
        return maxLen;
    }
}
