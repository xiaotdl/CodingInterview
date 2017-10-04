package backpack_ii;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn), can be improved to O(n) thru 滚动优化
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || A.length == 0 || V == null || V.length == 0) {
            return 0;
        }

        // dp[i][j]: max value when pick from first i items(0/1 A[i-1]) with a total size of j, backpack(0..j, A[0..i], V[0..i])
        int[][] f = new int[A.length + 1][m + 1];

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                f[i][j] = Math.max(
                    f[i - 1][j], // doesn't pick i-th item
                    j >= A[i - 1] ? f[i - 1][j - A[i - 1]] + V[i - 1] : 0 // pick i-th item
                );
            }
        }

        return f[A.length][m];
    }
}
