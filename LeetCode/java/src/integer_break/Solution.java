package integer_break;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public int integerBreak(int n) {
        // dp[i]: dp[i] records the max product so far till i.
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }
}

// tag: math
// time: O(n)
// space: O(1)
class SolutionII {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;
        return product;
    }
}
