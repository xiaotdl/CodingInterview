package best_time_to_buy_and_sell_stock_iii;

/**
 * Created by Xiaotian on 12/27/16.
 */
// tag: dp
// time: O(n)
// space: O(n)
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        // before[i]: maxProfit of one transaction before day i (inclusive)
        // after[i]: maxProfit of one transaction after day i (inclusive)
        int[] before = new int[n];
        int[] after = new int[n];

        // dp from left to right
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            before[i] = Math.max(before[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        // dp from right to left
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            after[i] = Math.max(after[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, before[i] + after[i]);
        }
        return maxProfit;
    }
}
