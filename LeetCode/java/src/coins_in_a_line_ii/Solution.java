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

        return memSearch(values, dp, isVisited, values.length) > sum / 2;
    }

    private int memSearch(int[] values, int[] dp, boolean[] isVisited, int n) {
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
                    memSearch(values, dp, isVisited, n - 2),
                    memSearch(values, dp, isVisited, n - 3)
                ),
                // take 2 coins
                values[l - n]
                + values[l - n + 1]
                + Math.min(
                    memSearch(values, dp, isVisited, n - 3),
                    memSearch(values, dp, isVisited, n - 4)
                )
            );
        }
        return dp[n];
    }
}

class SolutionII {
    // tag: dp
    // time: O(n)
    // space: O(n)
    /*
     * @param V: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] V) {
        if (V.length <= 2) return true;
        int n =  V.length;

        int sum = 0;
        for (int v : V) sum += v;

        // dp[i]: max values of coins one can get starting from V[i]
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = V[n - 1];
        dp[n - 2] = V[n - 2] + V[n - 1];
        dp[n - 3] = V[n - 3] + V[n - 2];
        for (int i = n - 4; i >= 0; i--) {
            dp[i] = Math.max(
                    V[i] + Math.min(dp[i + 2], dp[i + 3]),
                    V[i] + V[i + 1] + Math.min(dp[i + 3], dp[i + 4])
            );
        }
        return dp[0] > sum/2;
    }
}
