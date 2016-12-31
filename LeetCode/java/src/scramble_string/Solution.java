package scramble_string;

/**
 * Created by Xiaotian on 5/19/16.
 */
public class Solution {
    // tag: string, dp
    // time: O(n^4), 3 for loops with one pass through a string in each iteration
    // space: O(n^3),used three dimensional matrix to save path
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }

        int n = s1.length();
        // dp[i][j][l]: tracks isScramble(s1[i...i+l-1], s2[j...j+l-1])
        boolean[][][] dp = new boolean[n][n][n + 1];
        // when there is only one char, l = 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int l = 2; l <= n; l++) { // substring length
            for (int i = 0; i <= n - l; i++) {  // s1[i...i+l-1]
                for (int j = 0; j <= n - l; j++) { // s2.[j...j+l-1]
                    boolean canScramble = false;
                    for (int k = 1; k < l; k++) {
                        // canScramble = isScramble(s11, s21) && isScramble(s12, s22)
                        //            || isScramble(s11, s22) && isScramble(s12, s21)
                        canScramble = dp[i][j][k] && dp[i + k][j + k][l - k]
                                   || dp[i][j + l - k][k] && dp[i + k][j][l - k];
                        if (canScramble) break;
                    }
                    dp[i][j][l] = canScramble;
                }
            }
        }

        return dp[0][0][n];
    }
}
