package dungeon_game;

/**
 * Created by Xiaotian on 12/26/16.
 */
// reverse thinking
// dp from bottom-right to top-left
// tag: dp
// time: O(n^2)
// space: O(n^2)
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        // dp[i][j]: min HP needed at position[i][j]
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(
                        Math.max(1, dp[i + 1][j] - dungeon[i][j]),
                        Math.max(1, dp[i][j + 1] - dungeon[i][j]));
            }
        }
        return dp[0][0];
    }
}
