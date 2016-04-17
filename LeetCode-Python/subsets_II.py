# Given a collection of integers that might contain duplicates, S, return all possible subsets.

# Note:
# Elements in a subset must be in non-descending order.
# The solution set must not contain duplicate subsets.
# For example,
# If S = [1,2,2], a solution is:

# [
#   [2],
#   [1],
#   [1,2,2],
#   [2,2],
#   [1,2],
#   []
# ]

class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup_1_dfs(self, S):

        def dfs(depth, start, valuelist):
            if valuelist not in res: res.append(valuelist)
            if depth == len(S): return
            for i in xrange(start, len(S)):
                dfs(depth + 1, i + 1, valuelist + [S[i]])

        if not S: return
        S.sort()
        res = []
        dfs(0, 0, [])
        return res