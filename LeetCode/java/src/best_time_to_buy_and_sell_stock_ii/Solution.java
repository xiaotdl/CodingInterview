package best_time_to_buy_and_sell_stock_ii;

/**
 * Created by Xiaotian on 12/27/16.
 */
// tag: dp
// time: O(n)
// space: O(1)
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }
}
