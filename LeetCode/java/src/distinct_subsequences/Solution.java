package distinct_subsequences;

import java.util.concurrent.TimeUnit;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // "When you see a string problem about subsequence or matching, dynamic programming should come to your mind naturally."
    //  empty string is a subsequence of any string but only 1 time."
    // tag: str, dp
    // time: O(m+n), one pass through string
    // space: O(mn), used two dimensional matrix to save path
    public int numDistinct(String s, String t) {
        // dp[i][j]: tracks the num of subsequences of t[:j](t[0...j-1]) in s[:i](s[0...i-1])
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // when s is empty
        for (int j = 1; j < t.length(); j++) {
            dp[0][j] = 0;
        }
        // when t is empty
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(String.valueOf(dp[i][j]) + " ");
            }
            System.out.println();
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        new Solution().numDistinct("rabbbit", "rabbit");
    }
}

class SolutionII {
    // same as SolutionI
    // tag: str, dp
    // time: O(m+n), one pass through string
    // space: O(mn), used two dimensional matrix to save path
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return 0;

        int m = s.length();
        int n = t.length();
        // dp[i][j]: numDistinct of ""||s[0..i-1] and ""||t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= i && j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
