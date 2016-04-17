public class LongestCommonSubstring {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */

    // V1, O(mn)
    public int longestCommonSubstring(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }

        int maxLength = 0;
        int m = A.length();
        int n = B.length();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = 0;
                while (i + len < m && j + len < n
                      && A.charAt(i + len) == B.charAt(j + len)) {
                    len++;
                    maxLength = Math.max(maxLength, len);
                }
            }
        }

        return maxLength;
    }
}

