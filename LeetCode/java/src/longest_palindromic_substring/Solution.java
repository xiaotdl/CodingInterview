package longest_palindromic_substring;

/**
 * Created by Xiaotian on 6/30/17.
 */
public class Solution {
    // tag: str, dp
    // time: O(n^2)
    // space: O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;

        char[] S = s.toCharArray();
        int n = s.length();
        //dp[i][j]: isPalindrome(s[i..j])
        boolean[][] dp = new boolean[n][n];

        int max = 0;
        String res = null;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = S[i] == S[j] && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}

class SolutionII {
    // expanding from potential palindrome centers
    // 2n - 1 centers in total: n centers on char and n - 1 centers between chars
    // tag: str, ptr
    // time: O(n^2)
    // space: O(1)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;

        int l = 0, r = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            if (len1 > len2 && len1 > max) {
                l = i - len1 / 2;
                r = i + len1 / 2;
                max = len1;
            }
            else if (len2 > len1 && len2 > max) {
                l = i - len2 / 2 + 1;
                r = i + len2 / 2;
                max = len2;
            }
        }
        return s.substring(l, r + 1);
    }

    private int expandAroundCenter(String s, int l, int r) {
        while (l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}

