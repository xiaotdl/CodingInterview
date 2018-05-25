package largest_plus_sign;

import java.util.*;

/**
 * Created by Xiaotian on 5/1/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/largest-plus-sign/solution/
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> mineSet = new HashSet<>();
        int[][] dp = new int[N][N]; // min consecutive empty slots from up/down/left/right direction

        for (int[] mine : mines) {
            mineSet.add(mine[0]*N + mine[1]);
        }

        int max = 0;
        int cnt = 0; // length of side length
        for (int i = 0; i < N; i++) {
            cnt = 0; // empty slots on left side
            for (int j = 0; j < N; j++) {
                cnt = mineSet.contains(i*N + j) ? 0 : cnt + 1;
                dp[i][j] = cnt;
            }
            cnt = 0; // empty slots on right side
            for (int j = N - 1; j >= 0; j--) {
                cnt = mineSet.contains(i*N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
        }

        for (int j = 0; j < N; j++) {
            cnt = 0; // empty slots on up side
            for (int i = 0; i < N; i++) {
                cnt = mineSet.contains(i*N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
            cnt = 0; // empty slots on down side
            for (int i = N - 1; i >= 0; i--) {
                cnt = mineSet.contains(i*N + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
