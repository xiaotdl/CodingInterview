# Say you have an array for which the ith element is the price of a given stock on day i.

# If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit_1(self, prices):
        if len(prices) < 2:
            return 0
        min = prices[0]
        max_diff = 0
        for i in xrange(1, len(prices)):
            if prices[i - 1] < min:
                min = prices[i - 1]
            curr_diff = prices[i] - min
            if curr_diff > max_diff:
                max_diff = curr_diff
        return max_diff