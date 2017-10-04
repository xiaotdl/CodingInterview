package burst_balloons;

/**
 * Created by Xiaotian on 12/25/16.
 */
public class Solution {
    // tag: dp
    // time: O(n^3)
    // space: O(n^2)
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i][j]: records the max coins gained when burst balloons from i to j
        int[][] dp = new int[nums.length][nums.length];
        for (int len = 1; len <= nums.length; len++) {
            // i: start; j: end; k: ptr
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len - 1;
                // balloon k is the last to burst,
                // balloons then can be separated into 2 sections: i~k-1, k+1~j
                for (int k = i; k <= j; k++) {
                    int leftBound = (i != 0 ? nums[i - 1] : 1);
                    int rightBound = (j != nums.length - 1 ? nums[j + 1] : 1);
                    int lastBalloonCoins = leftBound*nums[k]*rightBound;
                    int leftCoins = (k != i ? dp[i][k - 1] : 0);
                    int rightCoins = (k != j ? dp[k + 1][j] : 0);
                    dp[i][j] = Math.max(dp[i][j],
                            lastBalloonCoins + leftCoins + rightCoins);
                }
            }
        }
        return dp[0][nums.length - 1];
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^3)
    // space: O(n^2)
    /*
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        // dp[i][j]: records the max coins gained when burst ballons from i to j
        int[][] dp = new int[n + 2][n + 2];
        boolean[][] isVisited = new boolean[n + 2][n + 2];
        int[] A = new int[n + 2];
        A[0] = 1;
        for (int i = 1; i <= n; i++) {
            A[i] = nums[i - 1];
        }
        A[n + 1] = 1;
        return memSearch(A, dp, isVisited, 1, n);
    }

    private int memSearch(int[] A, int[][] dp, boolean[][] isVisited, int l, int r) {
        if (isVisited[l][r]) {
            return dp[l][r];
        }
        isVisited[l][r] = true;

        if (l > r) return 0;

        int max = 0;
        for (int k = l; k <= r; k++) {  // enumerate last balloon to burst
            int midCoins = A[l - 1] * A[k] * A[r + 1];
            int leftCoins = memSearch(A, dp, isVisited, l, k - 1);
            int rightCoins = memSearch(A, dp, isVisited, k + 1, r);
            max = Math.max(max, leftCoins + midCoins + rightCoins);
        }
        dp[l][r] = max;
        return dp[l][r];
    }
}
