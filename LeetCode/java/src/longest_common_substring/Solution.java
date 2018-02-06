package longest_common_substring;

/**
 * Created by Xiaotian on 2/6/18.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /**
     * @param a, b: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return 0;
        }

        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        // dp[i][j]: lcs(""||A[0..i-1], ""||B[0..j-1]) ended with A[i - 1] & B[j - 1], 因为要连续
        int[][] dp = new int[A.length + 1][B.length + 1];
        dp[0][0] = 0;
        for (int i = 1; i < A.length + 1; i++) dp[i][0] = 0;
        for (int j = 1; j < B.length + 1; j++) dp[0][j] = 0;
        int max = 0;
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = 0; // 0 as substring needs to be continuous
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

