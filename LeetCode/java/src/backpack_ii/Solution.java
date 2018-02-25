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

class SolutionII {
    // Same as Solution
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /*
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // dp[i][j]: max value when pick from first i items with size of j
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i < A.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j],
                        j >= A[i - 1] ? dp[i - 1][j - A[i - 1]] + V[i - 1] : 0);
            }
        }
        printSelectedItems(dp, A, V, m);
        return dp[A.length][m];
    }

    // backtracking selected items
    private void printSelectedItems(int[][] f, int[] A, int[] V, int S) {
        int m = f.length;
        int n = f[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = m - 1; i >= 1 && S > 0; i--) {
            if (f[i][S] == f[i - 1][S]) continue;
            System.out.println("Taking item: " + (i-1) + " size: " + A[i-1] + " val: " + V[i-1]);
            S -= A[i-1];
        }
        System.out.println();
    }
}
