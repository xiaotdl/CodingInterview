package palindrom_partitioning_ii;

/**
 * Created by Xiaotian on 12/28/16.
 */
// TLE: calculated isPalindrome(s, j, i) too many times
// tag: dp
// time: O(n^3)
// space: O(n)
public class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        // dp[i]: minCut of s[0..i]
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(s, j, i)) {
                    dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public boolean isPalindrome(String s, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) return false;
        boolean isPalindrom = true;
        int i = fromIndex;
        int j = toIndex;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                isPalindrom = false;
            }
            i++;
            j--;
        }
        return isPalindrom;
    }
}

// time optimized version of SolutionI
// tag: dp
// time: O(n^2)
// space: O(n^2)
class SolutionII {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        // prepare
        boolean[][] isPalindrome = getIsPalindrome(s);

        int n = s.length();
        // dp[i]: minCut of s[0..i]
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) {
                    dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public boolean[][] getIsPalindrome(String s) {
        // isP[i][j]: s[i..j] is a palindrome
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }
}


class SolutionIII {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public int minCut(String s) {
        // isPalindrome[i][j]: s[i..j] is palindrome
        boolean[][] isPalindrome = prepare(s);
        // f[i]: cntOfPalindromes(""||s[0..i-1])
        int[] f = new int[s.length() + 1];
        f[0] = 0;
        for (int i = 1; i < s.length() + 1; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j <= i - 1; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[s.length()] - 1;

    }

    private boolean[][] prepare(String s) {
        char[] S = s.toCharArray();
        int n = s.length();

        // f[i][j]: isPalindrome(s[i..j])
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = i + len - 1; j < n; i++, j++) {
                f[i][j] = (i + 1 <= j - 1 ? f[i + 1][j - 1] : true)
                        && S[i] == S[j];
            }
        }
        return f;
    }
}
