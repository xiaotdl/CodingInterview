# Given a set of distinct integers, S, return all possible subsets.

# Note:
# Elements in a subset must be in non-descending order.
# The solution set must not contain duplicate subsets.
# For example,
# If S = [1,2,3], a solution is:

# [
#   [3],
#   [1],
#   [2],
#   [1,2,3],
#   [1,3],
#   [2,3],
#   [1,2],
#   []
# ]

class Solution:
    # @param S, a list of integer
    # @return a list of lists of integer
    def subsets_1_dfs(self, S):
        if not S: return
        def dfs(depth, start, valuelist):
            res.append(valuelist)
            if depth == len(S): return
            for i in xrange(start, len(S)):
                dfs(depth + 1, i + 1, valuelist + [S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res