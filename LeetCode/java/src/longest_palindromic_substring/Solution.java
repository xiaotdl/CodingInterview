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

class SolutionIII {
    // insert # at even indexes to make a string always odd
    // #a#
    // #a#b#a#
    // Note: i < 2n, cnt <= 2n
    // tag: str, ptr
    // time: O(n^2)
    // space: O(1)
    /*
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        int max = 0;
        String res = "";
        for (int i = 0; i < 2*n; i++) {
            int cnt = 1;
            while (i - cnt >= 0 && i + cnt <= 2*n && get(s, i - cnt) == get(s, i + cnt)) {
                cnt++;
            };
            cnt--;
            if (cnt > max) {
                res = s.substring((i - cnt) / 2, (i + cnt) / 2);
                max = cnt;
            }
        }
        return res;
    }

    private char get(String s, int i) {
        if (i % 2 == 0) {
            return '#';
        } else {
            return s.charAt(i / 2);
        }
    }
}

class SolutionIV {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;

        int start = 0;
        int end = 0;
        int maxLen = 0;
        int n = s.length();
        char[] S = s.toCharArray();

        boolean[][] dp = new boolean[n][n];
        // init dp
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            if (S[j] == S[j - 1]) {
                dp[j - 1][j] = true;
                maxLen = 2;
                start = j - 1;
                end = j;
            }
        }
        // find the maxlen palindrome subtring
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = len + i - 1;
                if (dp[i + 1][j - 1] && S[i] == S[j]) {
                    dp[i][j] = true;
                    if (maxLen < len) {
                        start = i;
                        end = j;
                        maxLen = len;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
