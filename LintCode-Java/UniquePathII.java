public class UniquePathII {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */

    // V1, O(n^2)
    // DP(matrix)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] sum = new int[m][n];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                sum[0][i] = 0;
                break;
            } else {
                sum[0][i] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                sum[i][0] = 0;
                break;
            } else {
                sum[i][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    sum[i][j] = 0;
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
                }
            }
        }

        return sum[m - 1][n - 1];
    }
}

