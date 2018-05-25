package palindromic_substrings;

/**
 * Created by Xiaotian on 4/25/18.
 */
class Solution {
    // Same as longest palindromic substring
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        char[] S = s.toCharArray();

        int cnt = 0;
        boolean[][] dp = new boolean[n][n];
        // init dp
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            cnt++;
        }
        for (int j = 1; j < n; j++) {
            if (S[j] == S[j - 1]) {
                dp[j - 1][j] = true;
                cnt++;
            }
        }
        // find the maxlen palindrome subtring
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = len + i - 1;
                if (dp[i + 1][j - 1] && S[i] == S[j]) {
                    dp[i][j] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
