package best_time_to_buy_and_sell_stock_with_transaction_fee;

import java.util.*;

/**
 * Created by Xiaotian on 5/3/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/126180/C++-DP-solution-summary
    // tag: dp
    // time: O(n)
    // space: O(n), can be improved to O(1) using prevBuy and prevSell
    public int maxProfit(int[] prices, int fee) {
        int[] buy = new int[prices.length + 1]; // minCost till day i
        Arrays.fill(buy, Integer.MAX_VALUE);
        int[] sell = new int[prices.length + 1]; // maxProfit till day i

        for (int i = 0; i < prices.length; i++) {
            buy[i + 1] = Math.min(buy[i], prices[i] + fee - sell[i]);
            sell[i + 1] = Math.max(sell[i], prices[i] - buy[i]);
        }
//        System.out.println(Arrays.toString(buy));
//        System.out.println(Arrays.toString(sell));
        return sell[prices.length];
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(new Solution().maxProfit(prices, fee));
        //               1, 3, 2, 8, 4, 9
        // buy  MAX_INT  3  3  3  3  1  1
        // sell   0      0  0  0  5  5  8
        // buy  prices[0] = 1
        // sell prices[3] = 8
        // buy  prices[4] = 4
        // sell prices[5] = 9
        // max profit: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
    }
}

