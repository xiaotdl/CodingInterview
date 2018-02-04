package backpack_iv;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // tag: dp
    // time: O(mn*m/v), v: avg value in A
    // space: O(mn)
    /*
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] A, int m) {
        if (A == null || A.length == 0) return 0;

        // dp[i][j]: ways to pick from first i items(0..m/A[i-1] A[i-1]) with a total size of j, backpack(A[0..i], 0..j)
        int[][] dp = new int[A.length + 1][m + 1];

        dp[0][0] = 1;
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 0; j <= m; j++) {
                int k = 0;
                while (k * A[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - k * A[i - 1]];
                    k++;
                }
            }
        }
        return dp[A.length][m];
    }
}

class SolutionII {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /*
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] A, int m) {
        if (A == null || A.length == 0) return 0;

        // dp[i][j]: ways to pick from first i items with a total size of j
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i < A.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j]
                         + (j >= A[i - 1] ? dp[i][j - A[i - 1]] : 0);
            }
        }
        return dp[A.length][m];
    }
}
