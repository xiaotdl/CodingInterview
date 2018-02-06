package longest_common_subsequence;

/**
 * Created by Xiaotian on 10/2/17.
 */
public class Solution {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }

        int m = A.length();
        int n = B.length();
        // dp[i][j]: longestCommonSubsequence(""||A[0..i-1], ""||B[0..j-1])
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) dp[i][0] = 0;
        for (int j = 0; j < n; j++) dp[0][j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

class SolutionII {
    // Same as Solution
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of a and b.
     */
    public int longestCommonSubsequence(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return 0;
        }

        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        // dp[i][j]: lcs(""||A[0..i-1], ""||B[0..j-1]) ended with A[i-1] | B[j-1] | (A[i-1]&B[j-1]), 因为不需要连续
        int[][] dp = new int[A.length + 1][B.length + 1];
        dp[0][0] = 0;
        for (int i = 1; i < A.length + 1; i++) dp[i][0] = 0;
        for (int j = 1; j < B.length + 1; j++) dp[0][j] = 0;
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // subsequence doesn't need to be continuous
                }
            }
        }
        return dp[A.length][B.length];
    }
}


