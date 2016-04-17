# Write a function to find the longest common prefix string amongst an array of strings.

class Solution:
    # @return a string
    def longestCommonPrefix_1(self, strs):
        if not strs: return ""
        for i in xrange(len(strs[0])):
            for j in xrange(len(strs)):
                if len(strs[j]) <= i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]
        return strs[0]