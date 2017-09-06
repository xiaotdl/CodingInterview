package best_time_to_buy_and_sell_stock;

/**
 * Created by Xiaotian on 12/27/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(n)
    // space: O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int max = 0;
        // min price till (including) day i-1
        int prevMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - prevMin);
            prevMin = Math.min(prevMin, prices[i]);
        }
        return max;
    }
}

class SolutionII {
    // Same as Solution
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int minSoFar = prices[0];
        int maxEndingHere = 0;
        for (int i = 1; i < prices.length; i++) {
            maxEndingHere = Math.max(maxEndingHere, prices[i] - minSoFar);
            minSoFar = Math.min(minSoFar, prices[i]);
        }
        return maxEndingHere;
    }
}
