# Given an index k, return the kth row of the Pascal's triangle.

# For example, given k = 3,
# Return [1,3,3,1].

# Note:
# Could you optimize your algorithm to use only O(k) extra space?

class Solution:
    # @return a list of integers
    def getRow_1(self, rowIndex):
        if rowIndex < 0: return
        res = [0 for i in xrange(rowIndex + 1)]
        for i in xrange(0, len(res)):
            res[i] = 1
            for j in xrange(i - 1, 0, -1):
                res[j] = res[j] + res[j - 1]
        return res