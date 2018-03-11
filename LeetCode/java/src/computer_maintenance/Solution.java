package computer_maintenance;

/**
 * Created by Xiaotian on 3/8/18.
 */
class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /**
     * @param n: the rows of matrix
     * @param m: the cols of matrix
     * @param badcomputers: the bad computers
     * @return: The answer
     */
    public int maintenance(int m, int n, Point[] badcomputers) {
        int[][] matrix = new int[m][n];
        for (Point p : badcomputers) {
            matrix[p.x][p.y] = 1;
        }

        // dp[i][j]: min cost fixing till row i and exit from j side (left: j=0, right: j=1)
        int[][] dp = new int[m][2];

        for (int i = 0; i < m; i++) {
            int rMost = -1;
            int lMost = -1;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    rMost = Math.max(rMost, j);
                    lMost = Math.max(lMost, n - 1 - j);
                }
            }

            if (i == 0) {
                if (rMost == -1) {
                    dp[0][0] = 0;
                    dp[0][1] = n - 1;
                }
                else {
                    dp[0][0] = 2 * rMost;
                    dp[0][1] = n - 1;
                }
                continue;
            }

            if (rMost == -1) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            }
            else {
                dp[i][0] = Math.min(dp[i - 1][0] + 2 * rMost, dp[i - 1][1] + n - 1) + 1;
                dp[i][1] = Math.min(dp[i - 1][1] + 2 * lMost, dp[i - 1][0] + n - 1) + 1;
            }
        }
        return Math.min(dp[m - 1][0], dp[m - 1][1]);
    }
}
