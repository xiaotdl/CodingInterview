# Given numRows, generate the first numRows of Pascal's triangle.

# For example, given numRows = 5,
# Return

# [
#      [1],
#     [1,1],
#    [1,2,1],
#   [1,3,3,1],
#  [1,4,6,4,1]
# ]

class Solution:
    # @return a list of lists of integers
    def generate_1_iterative(self, numRows):
        if numRows == 0: return []
        result = [[1]]
        while len(result) < numRows:
            tmp = [1]
            for i in xrange(0, len(result[-1]) - 1):
                tmp.append(result[-1][i] + result[-1][i + 1])
            tmp.append(1)
            result.append(tmp)
        return result