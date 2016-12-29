package best_time_to_buy_and_sell_stock_iv;

/**
 * Created by Xiaotian on 12/28/16.
 */
// tag: dp
// time: O(nk)
// space: O(nk)
public class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        if (k >= prices.length) return maxProfitUnlimitedTransactions(prices);
        int n = prices.length;
        // local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。
        // global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。
        int[][] local = new int[n][k + 1];
        int[][] global = new int[n][k + 1];
        for (int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(0, diff),
                                       local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[n - 1][k];
    }

    public int maxProfitUnlimitedTransactions(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}

// space optimized version of SolutionI
// tag: dp
// time: O(nk)
// space: O(k)
class SolutionII {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        if (k >= prices.length) return maxProfitUnlimitedTransactions(prices);
        // local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。
        // global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(0, diff),
                                    local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }

        return global[k];
    }

    public int maxProfitUnlimitedTransactions(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}

// https://www.youtube.com/watch?v=oDhu5uGq_ic&feature=youtu.be
// tag: dp
// time: O(nk^2)
// space: O(nk)
class SolutionIII {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        if (k >= prices.length) return maxProfitUnlimitedTransactions(prices);
        int n = prices.length;
        // buy/sell: records the money left on hand
        // sell[i][j]: max profit for up to i transactions by time j (0<=i<=k, 0<=j<=n).
        int[][] sell = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                int max = 0;
                for (int m = 0; m < j; m++) {
                    max = Math.max(max, prices[j] - prices[m] + sell[i - 1][m]);
                }
                sell[i][j] = Math.max(sell[i][j - 1], max);
            }
        }
        return sell[k][n - 1];
    }

    public int maxProfitUnlimitedTransactions(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}

// time optimized version of SolutionIII
// tag: dp
// time: O(nk)
// space: O(nk)
class SolutionIV {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        if (k >= prices.length) return maxProfitUnlimitedTransactions(prices);
        int n = prices.length;
        // buy/sell: records the money left on hand
        // sell[i][j]: max profit for up to i transactions by time j (0<=i<=k, 0<=j<=n).
        int[][] sell = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int buy = -prices[0];
            for (int j = 1; j < n; j++) {
                sell[i][j] = Math.max(sell[i][j - 1], prices[j] + buy);
                buy =  Math.max(buy, sell[i - 1][j - 1] - prices[j]);
            }
        }
        return sell[k][n - 1];
    }

    public int maxProfitUnlimitedTransactions(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}


