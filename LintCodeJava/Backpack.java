public class Backpack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */

    // V1, O(mn)
    // DP(matrix, backtrack)
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // 从前i个物品中取出一些组成体积和为j的背包, true/false
        boolean[][] f = new boolean[A.length + 1][m + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (j >= A[i - 1] && f[i - 1][j - A[i - 1]]) {
                    f[i][j] = true;
                } else {
                    f[i][j] = f[i - 1][j];
                }

            }
        }

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }
}

