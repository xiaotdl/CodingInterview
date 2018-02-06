package regular_expression_matching;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    /**
     * f[i][j]: if s[0..i-1] matches p[0..j-1]
     * if p[j - 1] != '*'
     *      f[i][j] = f[i - 1][j - 1] && s[i - 1] == p[j - 1]
     * if p[j - 1] == '*', denote p[j - 2] with x
     *      f[i][j] is true if any of the following is true
     *      1) "x*" repeats 0 time and matches empty: f[i][j - 2]
     *      2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
     * '.' matches any single character
     */
    // tag: str, dp
    // time: O(m*n)
    // space: O(m*n)
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
                    dp[i][j] = dp[i][j - 2] // * == 0
                            || (S[i - 1] == P[j - 2] || P[j - 2] == '.') && dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}

class SolutionII {
// 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
// 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
// 3, If p.charAt(j) == '*':
//   here are two sub conditions:
//      3.1   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
//                  dp[i][j] = dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
//                          or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
//                          or dp[i-1][j]              // in this case, a* counts as multiple a
//      3.2   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
    // tag: str, dp
    // time: O(m*n)
    // space: O(m*n)

    /**
     * @param s: A string
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] S = s.toCharArray();
        char[] P = p.toCharArray();

        //dp[i][j]: isMatch(""||s[0..i-1], ""||p[0..j-1])
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = (P[j - 1] == '*' && (j - 2 >= 0 ? dp[0][j - 2] : true));
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j == 1) {
                    if (i == 1) dp[1][1] = (S[0] == P[0] || P[0] == '.');
                    else dp[i][1] = false;
                }
                else if (P[j - 1] == '*') {
                    if (S[i - 1] == P[j - 2] || P[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2]     // * == 0
                                || dp[i - 1][j - 2] // * == 1
                                || dp[i - 1][j];    // * >= 2
                    }
                    else { // S[i - 1] != P[j - 2]
                        dp[i][j] = dp[i][j - 2]; // * == 0
                    }
                }
                else if (S[i - 1] == P[j - 1] || P[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[S.length][P.length];
    }
}
