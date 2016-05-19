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
        int len = s1.length();
        if (len == 0) {
            return true;
        }

        // dp[k][i][j]: tracks isScramble(s1[i...i+k], s2[j...j+k])
        boolean[][][] dp = new boolean[len][len][len];

        // when there is only one char (k = 1)
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[0][i][j] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        for (int k = 2; k <= len; k++) { // length of substring
            for (int i = len - k; i >= 0; i--) { // s1[i...i+k]
                for (int j = len - k; j >= 0; j--) { // s2[j...j+k]
                    boolean canScramble = false;
                    for (int m = 1; m < k; m++) { // cut string with substring length m
                        // s1 = a1 + b1; s2 = a2 + b2

                        canScramble = dp[m - 1][i][j] && dp[k - m - 1][i + m][j + m] // isScramble(a1, b1) && isScramble(a2, b2)
                                   || dp[m - 1][i][j + k - m] && dp[k - m - 1][i + m][j]; // isScramble(a1, b2) && isScramble(a2, b1)
                        if (canScramble) break;
                    }
                    dp[k - 1][i][j] = canScramble;
                }
            }
        }

        return dp[len - 1][0][0];
    }
}
