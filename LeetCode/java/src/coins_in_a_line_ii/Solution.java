package coins_in_a_line_ii;

/**
 * Created by Xiaotian on 10/2/17.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(n)
    /*
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // dp[i]: when there are i coins left, first coin value sum
        // return dp[n] > totalSum / 2;
        int[] dp = new int[values.length + 1];
        boolean[] isVisited = new boolean[values.length + 1];

        int sum = 0;
        for (int v : values) {
            sum += v;
        }

        return MemorySearch(values, dp, isVisited, values.length) > sum / 2;
    }

    private int MemorySearch(int[] values, int[] dp, boolean[] isVisited, int n) {
        if (isVisited[n]) {
            return dp[n];
        }
        isVisited[n] = true;

        int l = values.length;
        if (n == 0) {
            dp[n] = 0;
        } else if (n == 1) {
            dp[n] = values[l - 1];
        } else if (n == 2) {
            dp[n] = values[l - 1] + values[l - 2];
        } else if (n == 3) {
            dp[n] = values[l - 2] + values[l - 3];
        } else {
            dp[n] = Math.max(
                // take 1 coin
                values[l - n]
                + Math.min(
                    MemorySearch(values, dp, isVisited, n - 2),
                    MemorySearch(values, dp, isVisited, n - 3)
                ),
                // take 2 coins
                values[l - n]
                + values[l - n + 1]
                + Math.min(
                    MemorySearch(values, dp, isVisited, n - 3),
                    MemorySearch(values, dp, isVisited, n - 4)
                )
            );
        }
        return dp[n];
    }
}
