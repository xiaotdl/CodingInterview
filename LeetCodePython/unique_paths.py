# A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

# The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

# How many possible unique paths are there?

# Photo source: https://oj.leetcode.com/problems/unique-paths/

# Above is a 3 x 7 grid. How many possible unique paths are there?

# Note: m and n will be at most 100.

class Solution:
    # @return an integer
    def uniquePaths_1(self, m, n):
        # math solution: permutation and combination
        if n == 1 or m == 1:
            return 1
        elif n == 2:
            return m
        else:
            # result = (m-1+n-1)*...*(m-1+n-1-(m-2))/(m-1)!
            # result = (m+n-2)*...*n/(m-1)!
            result = 1
            a = m + n - 2
            while a >= n:
                result *= a
                a -= 1
            b = m - 1
            while b >= 1:
                result /= b
                b -= 1
            return result


    def uniquePaths_2(self, m, n):
        # time complexity: O(mn)
        # space complexity: O(mn)
        dp = [[0 for j in range(n)] for i in range(m)]
        for i in range(m): dp[i][0] = 1
        for j in range(n): dp[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
        return dp[m - 1][n - 1]
