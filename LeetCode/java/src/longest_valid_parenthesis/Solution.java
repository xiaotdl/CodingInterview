package longest_valid_parenthesis;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
// time: O(n)
// space: O(n)
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int max = 0;
        // dp[i]: longestValidParentheses of s[0..i]
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // if (s[i] == '(') continue;
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                    max = Math.max(max, dp[i]);
                }
                else { // s[i - 1] == ')'
                    if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                        dp[i] = 2 + dp[i - 1] + (i - 2 - dp[i - 1] >= 0 ? dp[i - 2 - dp[i - 1]] : 0);
                        max = Math.max(max, dp[i]);
                    }
                }
            }
        }
        return max;
    }
}
