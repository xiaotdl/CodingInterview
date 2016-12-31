package regular_expression_matching;

/**
 * Created by Xiaotian on 12/30/16.
 */
/**
 * f[i][j]: if s[0..i-1] matches p[0..j-1]
 * if p[j - 1] != '*'
 *      f[i][j] = f[i - 1][j - 1] && s[i - 1] == p[j - 1]
 * if p[j - 1] == '*', denote p[j - 2] with x
 *      f[i][j] is true iff any of the following is true
 *      1) "x*" repeats 0 time and matches empty: f[i][j - 2]
 *      2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
 * '.' matches any single character
 */
// tag: dp
// time: O(m*n)
// space: O(m*n)
public class Solution {
    public boolean isMatch(String s, String p) {
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
            dp[0][j] = '*' == P[j - 1] && (j - 2 >= 0 ? dp[0][j - 2] : true);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (P[j - 1] != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (S[i - 1] == P[j - 1] || P[j - 1] == '.');
                }
                else {
                    dp[i][j] = dp[i][j - 2] // x* matches 0 chars
                            || (S[i - 1] == P[j - 2] || P[j - 2] == '.') && dp[i - 1][j]; // x* matches >= 1 chars
                }
            }
        }
        return dp[m][n];
    }
}
