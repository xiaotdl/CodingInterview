public class BestTimeToBuyAndSellStockIV {
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */

    // V1, O(n*k), O(k)
    // DP
    // local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
    // global[i][j] = max(local[i][j], global[i - 1][j])
    public int maxProfit(int k, int[] prices) {
        if (prices == null || k <= 0 || prices.length < 2) {
            return 0;
        }

        // Improvement for case when k is much larger than number of days.
        if (k >= prices.length) {
            maxProfit2(prices);
        }

        int[] local = new int[k + 1];
        int[] global = new int[k + 1];

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j > 0; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }

        return global[k];
    }
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }
};
