package interleaving_string;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    // tag: str, dp
    // time: O(m*n)
    // space: O(m*n)
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;

        int m = s1.length();
        int n = s2.length();
        // dp[i][j]: isInterleave(""||s1[0..i-1], ""||s2[0..j-1], ""||s3[0..i+j-1])
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
                else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
                else {
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                            || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[m][n];
    }
}
