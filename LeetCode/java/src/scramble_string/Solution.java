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
        boolean[][][] dp = new boolean[len][len][len + 1];

        // when there is only one char, k = 1
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int k = 2; k <= len; k++) { // substring length
            for (int i = 0; i < len - k + 1; i++) {  // s1[i...i+k]
                for (int j = 0; j < len - k + 1; j++) { // s2.[j...j+k]
                    boolean canScramble = false;
                    for (int l = 1; l < k; l++) {
                        // canScramble = isScramble(s11, s21) && isScramble(s12, s22)
                        //            || isScramble(s11, s22) && isScramble(s12, s21)
                        canScramble = dp[i][j][l] && dp[i + l][j + l][k - l]
                                   || dp[i][j + k - l][l] && dp[i + l][j][k - l];
                        if (canScramble) break;
                    }
                    dp[i][j][k] = canScramble;
                }
            }
        }

        return dp[0][0][len];
    }
}
