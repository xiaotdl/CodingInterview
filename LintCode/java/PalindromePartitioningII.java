public class PalindromePartitioningII {
    /**
     * @param s a string
     * @return an integer
     */

    // V1, O(n^2)
    // DP(sequence)
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        boolean[][] isPalindrome = getIsPalindrome(s);

        int[] f = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            f[i] = i - 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()];
    }
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    // DP(interval)
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int len = 2; len < s.length(); len++) {
            for (int start = 0; start + len < s.length(); start++) {
                isPalindrome[start][start + len] = isPalindrome[start + 1][start + len - 1] && s.charAt(start) == s.charAt(start + len);
            }
        }

        return isPalindrome;
    }
};

