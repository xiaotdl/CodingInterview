public class BestTimeToBuyAndSellStockII {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */

    // V1, O(n), O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                profit += diff;
            }
        }

        return profit;
    }
};

