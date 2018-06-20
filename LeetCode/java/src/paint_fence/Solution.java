package paint_fence;

/**
 * Created by Xiaotian on 12/27/16.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(n)
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) return 0;
        if (n == 1) return k;
        // dp1[i]: numWays when the last two fences (whose indexes are i-1, i-2) have the same color.
        // dp2[i]: numWays when the last two fences have different colors.
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = k;
        dp1[1] = k;
        dp2[0] = k;
        dp2[1] = k * (k - 1);
        for (int i = 2; i < n; i++) {
            dp1[i] = dp2[i - 1];
            dp2[i] = (dp1[i - 1] + dp2[i - 1]) * (k - 1);
        }
        return dp1[n - 1] + dp2[n - 1];
    }
}
