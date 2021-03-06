package coin_change;

import java.util.Arrays;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    // tag: dp
    // time: O(amount*n)
    // space: O(amount)
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return 0;
        // dp[i]: coinChange(coins, i), (0<=i<=amount)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}


class SolutionII {
    // 重复选择 => 最小体积
    // tag: dp
    // time: O(amount*n)
    // space: O(amount)
    public int coinChange(int[] coins, int amount) {
        // dp[i]: coinChange(coins, i), (0<=i<=amount)
        //     i: amount
        //     v: min number of coins
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] < 0 || dp[i - coins[j]] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
}
