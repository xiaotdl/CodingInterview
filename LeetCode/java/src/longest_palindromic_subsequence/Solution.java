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

class SolutionII {
    // Same as Solution, with print demo
    // Ref: https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
//            for (int k = 0; k < i; k++) {
//                System.out.print("x" + " ");
//            }
            dp[i][i] = 1;
//            System.out.print(dp[i][i] + " ");
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print(dp[i][j] + " ");
            }
//            System.out.println();
        }
//        System.out.println();
//        System.out.println();
//        int n = s.length();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[0][s.length()-1];
    }
}
