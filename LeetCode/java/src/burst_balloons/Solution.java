package burst_balloons;

/**
 * Created by Xiaotian on 12/25/16.
 */
// tag: dp
// time: O(n^3)
// space: O(n^2)
public class Solution {
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
