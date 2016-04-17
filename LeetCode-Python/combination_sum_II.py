# Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

# Each number in C may only be used once in the combination.

# Note:
# All numbers (including target) will be positive integers.
# Elements in a combination (a1, a2, ... , ak) must be in non-descending order. (ie, a1 <= a2 <= ... ≤ ak).
# The solution set must not contain duplicate combinations.
# For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
# A solution set is: 
# [1, 7] 
# [1, 2, 5] 
# [2, 6] 
# [1, 1, 6] 

class Solution:
    # @param candidates, a list of integers
    # @param target, integer
    # @return a list of lists of integers
    def combinationSum2_1_dfs(self, candidates, target):

        def dfs(candidates, target, start, valuelist):
            if target == 0:
                if valuelist not in res: res.append(valuelist)
                return
            for i in xrange(start, len(candidates)):
                if target < candidates[i]:
                    return
                dfs(candidates, target - candidates[i], i + 1, valuelist + [candidates[i]])

        candidates.sort()
        res = []
        dfs(candidates, target, 0, [])
        return res