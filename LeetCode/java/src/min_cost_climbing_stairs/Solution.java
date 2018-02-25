package min_cost_climbing_stairs;

import java.util.*;

/**
 * Created by Xiaotian on 2/24/18.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(n)
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // f[i]: minCost to get stairs (i - 1)
        int[] f = new int[n + 2];
        f[0] = 0;
        f[1] = cost[0];
        for (int i = 2; i < n + 2; i++) {
            int currCost = (i == n + 1 ? 0 : cost[i - 1]);
            f[i] = Math.min(f[i - 1] + currCost,
                    f[i - 2] + currCost);
        }
        return Math.min(f[n], f[n + 1]);
    }
}
