public class _KSum {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */

    // V1, O(nkt)
    // DP(3D matrix)
    public int  kSum(int A[], int k, int target) {
        int[][][] f = new int[A.length + 1][k + 1][target + 1];

        for (int i = 0; i < A.length + 1; i++) {
            f[i][0][0] = 1;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < k + 1 && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j][t] + f[i - 1][j - 1][t - A[i - 1]];
                    } else {
                        f[i][j][t] = f[i - 1][j][t];
                    }
                }
            }
        }

        return f[A.length][k][target];
    }
}


