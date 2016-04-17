public class UniquePaths {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */

    // V1, O(n^2)
    // DP(matrix)
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] sum = new int[m][n];
        for (int i = 0; i < n; i++) {
            sum[0][i] = 1;
        }
        for (int j = 0; j < m; j++) {
            sum[j][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }

        return sum[m - 1][n - 1];
    }
}


