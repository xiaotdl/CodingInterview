# Given a collection of numbers, return all possible permutations.

# For example,
# [1,2,3] have the following permutations:
# [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

class Solution:
    # @param num, a list of integer
    # @return a list of lists of integers
    def permute_1_recursive(self, num):
        if len(num) == 0:
            return [[]]
        result = []
        for i in xrange(len(num)):\
            # take one num as the first fixed num, recursive
            tmp = num[:i] + num[i + 1:]
            for perm in self.permute(tmp):
                result.append([num[i]]+perm)
        return result