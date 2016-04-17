# Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

# For example, given the following triangle
# [
#      [2],
#     [3,4],
#    [6,5,7],
#   [4,1,8,3]
# ]
# The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

# Note:
# Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

class Solution:
    # @param triangle, a list of lists of integers
    # @return an integer
    def minimumTotal_1_dp(self, triangle):
        if len(triangle) == 0: return 0
        dp = [0 for i in xrange(len(triangle))]
        dp[0] = triangle[0][0]
        for i in xrange(1, len(triangle)):
            for j in xrange(len(triangle[i]) - 1, -1, -1):
                if j == len(triangle[i]) - 1:
                    dp[j] = dp[j - 1] + triangle[i][j]
                elif j == 0:
                    dp[j] = dp[j] + triangle[i][j]
                else:
                    dp[j] = min(dp[j - 1], dp[j]) + triangle[i][j]
        return min(dp)


    def minimumTotal_2(self, triangle):
        # O(1) extra space (using the input as temporary storage)
        for i in xrange(len(triangle)-2,-1,-1):
            for j in xrange(0,i+1):
                triangle[i][j] += min(triangle[i+1][j],triangle[i+1][j+1])
        return triangle[0][0]