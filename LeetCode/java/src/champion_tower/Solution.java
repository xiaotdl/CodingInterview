package champion_tower;

/**
 * Created by Xiaotian on 3/10/18.
 */
public class Solution {
    // 杨辉三角
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public double champagneTower(int poured, int query_row, int query_glass) {
        if (poured == 0) return 0.0;

        double[][] dp = new double[101][101];
        dp[0][0] = poured;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] > 1) {
                    double leftOver = dp[i][j] - 1;
                    dp[i][j] = 1;
                    dp[i + 1][j] += leftOver / 2;
                    dp[i + 1][j + 1] += leftOver / 2;
                }
            }
        }
        return dp[query_row][query_glass];
    }
}

