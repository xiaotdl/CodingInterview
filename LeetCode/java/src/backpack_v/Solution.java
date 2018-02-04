package backpack_v;

/**
 * Created by Xiaotian on 2/3/18.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /*
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] A, int m) {
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
                         + (j >= A[i - 1] ? dp[i - 1][j - A[i - 1]] : 0);
            }
        }
        return dp[A.length][m];
    }
}
