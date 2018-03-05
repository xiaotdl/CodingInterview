package stone_game;

/**
 * Created by Xiaotian on 10/2/17.
 */
public class Solution {
    // 由大到小考虑
    // tag: dp
    // time: O(n^3)
    // space: O(n^2)
    /*
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        // dp[i][j]: merge stones from i to j, max score
        int[][] dp = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];

        // for (int i = 0; i < n; i++) {
        //     dp[i][i] = 0;
        // }

        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }

        return memSearch(dp, isVisited, sum, 0, n - 1);
    }

    private int memSearch(int[][] dp, boolean[][] isVisited, int[][] sum, int l, int r) {
        if (isVisited[l][r]) {
            return dp[l][r];
        }
        isVisited[l][r] = true;

        if (l == r) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = l; k < r; k++) { // k < r as there is k-1 interval
            min = Math.min(
                min,
                sum[l][r]
                + memSearch(dp, isVisited, sum, l, k)
                + memSearch(dp, isVisited, sum, k + 1, r)
            );
        }
        dp[l][r] = min;
        return dp[l][r];
    }
}
