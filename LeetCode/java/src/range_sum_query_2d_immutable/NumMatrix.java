package range_sum_query_2d_immutable;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
public class NumMatrix {

    // time: O(n^2)
    // space: O(n^2)
    int [][] dp;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j]: sumRegion(0,0,i,j)
        dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    // time: O(1)
    // space: O(1)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sumRegion;
        if (row1 == 0 && col1 == 0) {
            sumRegion = dp[row2][col2];
        }
        else if (row1 == 0) {
            sumRegion = dp[row2][col2] - dp[row2][col1 - 1];
        }
        else if (col1 == 0) {
            sumRegion = dp[row2][col2] - dp[row1 - 1][col2];
        }
        else {
            sumRegion = dp[row2][col2] - dp[row2][col1 - 1] - dp[row1 - 1][col2] + dp[row1 - 1][col1 - 1];
        }
        return sumRegion;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
