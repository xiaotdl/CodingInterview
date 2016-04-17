# Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

# The same repeated number may be chosen from C unlimited number of times.

# Note:
# All numbers (including target) will be positive integers.
# Elements in a combination (a1, a2, ... , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).

# The solution set must not contain duplicate combinations.
# For example, given candidate set 2,3,6,7 and target 7, 
# A solution set is: 
# [7] 
# [2, 2, 3]

class Solution:
    # @param candidates, a list of integers
    # @param target, integer
    # @return a list of lists of integers
    def combinationSum_1_dfs(self, candidates, target):
        
        def dfs(candidates, target, start, valuelist):
            if target == 0:
                res.append(valuelist)
                return
            for i in xrange(start, len(candidates)):
                if target < candidates[i]:
                    return
                # if a num is not allowed to use multiple times, i=i+1
                dfs(candidates, target - candidates[i], i, valuelist + [candidates[i]])
        
        candidates.sort()
        res = []
        dfs(candidates, target, 0, [])
        return res