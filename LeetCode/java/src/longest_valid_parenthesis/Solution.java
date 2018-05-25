package longest_valid_parenthesis;

/**
 * Created by Xiaotian on 12/29/16.
 */

public class Solution {
    // credit: https://leetcode.com/articles/longest-valid-parentheses/#approach-2-using-dynamic-programming-accepted
    // tag: str, dp
    // time: O(n)
    // space: O(n)
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int max = 0;
        // dp[i]: longestValidParentheses(s[0..i])
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (i - 1 >= 0 && s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                }
                else if (i - 1 >= 0 && i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = 2 + dp[i - 1] + (i - 2 - dp[i - 1] >= 0 ? dp[i - 2 - dp[i - 1]] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}


class SolutionII {
    // credit: https://leetcode.com/problems/longest-valid-parentheses/solution/
    // tag: str
    // time: O(n)
    // space: O(1)
    public int longestValidParentheses(String s) {
        int maxlength = 0;
        int left, right;

        // left to right scan
        left = right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }

        // right to left scan
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
