package wildcard_matching;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    // tag: dp
    // time: O(m*n)
    // space: O(m*n)
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();
        char[] S = s.toCharArray();
        char[] P = p.toCharArray();

        // dp[i][j]: isMatch(""||s[0..i-1], ""||p[0..j-1])
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = '*' == P[j - 1] && dp[0][j - 1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (P[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (P[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] // '*' matches 0 char
                            || dp[i - 1][j]; // '*' matches >= 1 chars
                }
                else {
                    dp[i][j] = dp[i - 1][j - 1] && (S[i - 1] == P[j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
