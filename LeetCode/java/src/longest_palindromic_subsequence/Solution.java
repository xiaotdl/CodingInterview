package longest_palindromic_subsequence;

/**
 * Created by Xiaotian on 6/30/17.
 */
public class Solution {
    // 1. dp[i][j]: longestPalindromeSubseq(s[i..j])
    // 2. Init: dp[i][i] = 1
    // 3. State Transition:
    // S[i] == S[j] ? dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
    //              : dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
    // 4. Result: dp[0][n-1]
    // tag: str, dp
    // time: O(n^2)
    // space: O(n^2)
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] S = s.toCharArray();
        int n = s.length();
        // dp[i][j]: longestPalindromeSubseq(s[i..j])
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (S[i] == S[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
