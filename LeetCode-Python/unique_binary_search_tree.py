# Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

# For example,
# Given n = 3, there are a total of 5 unique BST's.

#    1         3     3      2      1
#     \       /     /      / \      \
#      3     2     1      1   3      2
#     /     /       \                 \
#    2     1         2                 3

class Solution:
    # @return an integer
    def numTrees_1(self, n):
        # intuitive, dynamic programming
        if n == 0 or n == 1:
            return 1
        if n == 2:
            return 2
        result = 0
        for i in range(0, n):
            result += self.numTrees(i)*self.numTrees(n-1-i)
        return result

    def numTrees_2(self, n):
        # save runtime
        dp = [0 for i in range(0, n + 1)]
        dp[0] = 1
        for node_num in range(1, n + 1):
            for left_sub_num in range(node_num):
                dp[node_num] += dp[left_sub_num]*dp[node_num - 1 - left_sub_num]
        return dp[n]