public class BackpackII {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */

    // V1, O(mn)
    // DP(matrix)
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || A.length == 0 || V == null || V.length == 0) {
            return 0;
        }

        // 从前i个物品中取出一些组成体积和为j的背包, max value
        int[][] f = new int[A.length + 1][m + 1];

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (A[i - 1] > j ) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }

        return f[A.length][m];
    }
}
