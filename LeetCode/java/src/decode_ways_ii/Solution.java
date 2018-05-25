package decode_ways_ii;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // credit: https://www.jiuzhang.com/solution/decode-ways-ii/
    // tag: dp
    // time: O(n)
    // space: O(n)
    private final static long MOD = 1000000007;
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] S = s.toCharArray();

        //dp[i]: numDecodings(""||s[0..i-1]
        long[] dp = new long[S.length + 1];
        dp[0] = 1;
        dp[1] = countOnes(S[0]);
        for (int i = 2; i < S.length + 1; i++) {
            int ones = countOnes(S[i - 1]);
            int tens = countTens(S[i - 2], S[i - 1]);
            dp[i] += dp[i - 1] * ones;
            dp[i] += dp[i - 2] * tens;
            dp[i] %= MOD;
        }
        return (int) dp[S.length];
    }

    private int countOnes(char c) {
        if (c == '*') return 9;
        if (1 <= c - '0' && c - '0' <= 9) return 1;
        return 0;
    }
    private int countTens(char c2, char c1) {
        if (c2 == '0') return 0;
        if (c2 == '1') {
            if (c1 == '*') return 9;
            return 1;
        }
        if (c2 == '2') {
            if (c1 == '*') return 6;
            if (c1 <= '6') return 1;
        }
        if ('3' <= c2 && c2 <= '9') return 0;
        if (c2 == '*') {
            if ('0' <= c1 && c1 <= '6') return 2;
            if ('7' <= c1 && c1 <= '9') return 1;
            if (c1 == '*') return 15;
        }
        return 0;
    }
}
