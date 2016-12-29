package best_time_to_buy_and_sell_stock_with_cooldown;

/**
 * Created by Xiaotian on 12/28/16.
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
        // buy/sell/cooldown[i]: before/include day i, max profit can be gained that ends with buy/sell/cooldown
        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] cooldown = new int[n];
        buy[0] = -prices[0];
        sell[0] = 0;
        cooldown[0] = 0;
        for (int i = 1; i < n; i++) {
            int price = prices[i];
            buy[i]  = Math.max(cooldown[i - 1] - price, buy[i - 1]);
            sell[i] = Math.max(buy[i - 1] + price, sell[i - 1]);
            cooldown[i] = Math.max(sell[i - 1], Math.max(buy[i - 1], cooldown[i - 1]));
        }
        return sell[n - 1];
    }
}

