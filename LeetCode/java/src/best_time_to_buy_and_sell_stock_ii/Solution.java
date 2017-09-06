package best_time_to_buy_and_sell_stock_ii;

/**
 * Created by Xiaotian on 12/27/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(n)
    // space: O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }
}
