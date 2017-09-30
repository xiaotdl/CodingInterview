package longest_continuous_increasing_subsequence_ii;

/**
 * Created by Xiaotian on 9/30/17.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /*
     * @param A: An integer matrix
     * @return: an integer
     */
    boolean[][] isVisited;
    int[][] dp;
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;

        int m = A.length;
        int n = A[0].length;
        isVisited = new boolean[m][n];
        // dp[i][j]: longestIncreasingContinuousSubsequenceII ending with A[i][j]
        dp = new int[m][n];
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         dp[i][j] = 1;
        //     }
        // }
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = search(A, i, j);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    private int search(int[][] A, int x, int y) {
        if (isVisited[x][y]) {
            return dp[x][y];
        }
        isVisited[x][y] = true;

        int max = 1;
        for (int k = 0; k < 4; k++) {
            int nextX = x + dx[k];
            int nextY = y + dy[k];
            if (inBound(A.length, A[0].length, nextX, nextY)
                && A[x][y] > A[nextX][nextY]) {
                max = Math.max(max, search(A, nextX, nextY) + 1);
            }
        }
        return max;
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x < m
            && 0 <= y && y < n;
    }
}
