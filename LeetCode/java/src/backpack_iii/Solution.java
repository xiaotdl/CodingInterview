package backpack_iii;

/**
 * Created by Xiaotian on 2/3/18.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /*
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // dp[i][j]: max value when pick from first i items (multiple times allowed) with size of j
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i < A.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j],
                        j >= A[i - 1] ? dp[i][j - A[i - 1]] + V[i - 1] : 0);
            }
        }
        return dp[A.length][m];
    }
}
