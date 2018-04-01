package decode_ways;

/**
 * Created by Xiaotian on 6/13/17.
 */
public class Solution {
    // tag: str, dp
    // time: O(n)
    // space: O(n)
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] S = s.toCharArray();
        //dp[i]: numDecodings(""||s[0..i-1]
        int[] dp = new int[S.length + 1];
        dp[0] = 1;
        dp[1] = (1 <= (S[0] - '0') && (S[0] - '0') <= 9) ? 1 : 0;
        for (int i = 2; i < S.length + 1; i++) {
            int ones = S[i - 1] - '0';
            int tens = 10 * (S[i - 2] - '0') + ones;
            if (1 <= ones && ones <= 9) {
                dp[i] += dp[i - 1];
            }
            if (10 <= tens && tens <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[S.length];
    }
}
