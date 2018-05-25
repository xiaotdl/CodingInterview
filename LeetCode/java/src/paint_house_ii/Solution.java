package paint_house_ii;

/**
 * Created by Xiaotian on 12/23/16.
 */
public class Solution {
    // tag: dp
    // time: O(n*k^2)
    // space: O(1), in place
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int houses = costs.length;
        int colors = costs[0].length;
        for (int i = 1; i < houses; i++) {
            for (int j = 0; j < colors; j++) {
                costs[i][j] += _min(costs[i - 1], j);
            }
        }
        return _min(costs[costs.length - 1]);
    }

    int _min(int[] list, int except) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            if (i == except) continue;
            min = Math.min(min, list[i]);
        }
        return min;
    }

    int _min(int[] list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            min = Math.min(min, list[i]);
        }
        return min;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n*k^2)
    // space: O(n*k)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        // dp[i][j]: minCost so far with paiting house i with color j
        int[][] dp = new int[n][k];
        for (int j = 0; j < k; j++) dp[0][j] = costs[0][j];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l == j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + costs[i][j]);
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int _minCost : dp[n - 1]) {
            minCost = Math.min(minCost, _minCost);
        }
        return minCost;
    }
}


class SolutionIII {
    // credit: leetcode.com/problems/paint-house-ii/discuss/69492/AC-Java-solution-without-extra-space/71565
    // Java O(n * k) time, O(1) space, without modify costs array solution.
    // We record the min1 value and min1 index, if j == min1index use min2.
    // tag: dp
    // time: O(n*k)
    // space: O(1)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        int prevMin1 = 0; // first minCost till prev house
        int prevMin2 = 0; // second minCost till prev house
        int prevMin1Index = -1; // idx to first minCost
        for (int i = 0; i < n; i++) { // foreach house
            int currMin1 = Integer.MAX_VALUE;
            int currMin2 = Integer.MAX_VALUE;
            int currMin1Index = -1;
            for (int j = 0; j < k; j++) { // foreach color
                int currMinCost = costs[i][j] + (j == prevMin1Index ? prevMin2 : prevMin1); // minCost till curr house with color j

                if (currMinCost < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = currMinCost;
                    currMin1Index = j;
                }
                else if (currMinCost < currMin2) {
                    currMin2 = currMinCost;
                }
            }

            prevMin1 = currMin1;
            prevMin2 = currMin2;
            prevMin1Index = currMin1Index;
        }
        return prevMin1;
    }
}
