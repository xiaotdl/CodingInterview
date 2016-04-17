public class DistinctSubsequences {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */

    // V1, O(mn)
    // DP(two sequence)
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() == 0) {
            return 0;
        }

        int m = S.length();
        int n = T.length();
        int[][] sum = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            sum[i][0] = 1;
        }
//        for (int i = 1; i < n + 1; i++) {
//            sum[0][i] = 0;
//        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    sum[i][j] = sum[i - 1][j - 1] + sum[i - 1][j];
                } else {
                    sum[i][j] = sum[i - 1][j];
                }
            }
        }

        return sum[m][n];
    }
}

