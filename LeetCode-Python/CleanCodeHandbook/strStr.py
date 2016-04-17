class Solution:
    # @param {string} source
    # @param {string} target
    # @return {integer}
    def strStr(self, source, target):
        # [brute force]
        # O(nm) runtime, O(1) space
        if source is None and target is None:
            return -1
        if target == "":
            return 0
        if len(target) > len(source):
            return -1

        for i in range(len(source)):
            for j in range(len(target)):
                if i + j > len(source) - 1:
                    return -1
                if source[i + j] != target[j]:
                    break
                if j == len(target) - 1:
                    return i

        return -1


print Solution().strStr('abc', 'b')
print Solution().strStr('abcdde', 'cdde')
# >>>
# 1
# 2
