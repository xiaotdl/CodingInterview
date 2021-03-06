package backpack;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(mn), can be improved to O(n) thru 滚动优化
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // dp[i][j]: can pick from first i items(0/1 A[i-1]) with a total size of j, backpack(0..j, 0..i)
        boolean[][] dp = new boolean[A.length + 1][m + 1];

        dp[0][0] = true;

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j] // doesn't pick i-th item, A[i - 1]
                        || (j >= A[i - 1] && dp[i - 1][j - A[i - 1]]); // pick i-th item, A[i - 1]
            }
        }

        for (int j = m; j >= 0; j--) {
            if (dp[A.length][j]) {
                return j;
            }
        }
        return 0;
    }
}

class SolutionII {
    // Same as Solution
    // tag: dp
    // time: O(mn)
    // space: O(mn)
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean[][] dp = new boolean[A.length + 1][m + 1]; // dp[i][j]: can pick a few from first i items, that sums to size j
        dp[0][0] = true;
        for (int i = 0; i < A.length + 1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < m + 1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j]
                        || (j >= A[i - 1] && dp[i - 1][j - A[i - 1]]);
            }
        }

        int max = 0;
        for (int i = m; i > 0; i--) {
            if (dp[A.length][i]) {
                max = i;
                break;
            }
        }
        return max;
    }
}
