# Implement strStr().

# Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

class Solution:
    # @param {string} source
    # @param {string} target
    # @return {integer}
    def strStr(self, source, target):
        # O(mn), O(1)
        if source == None or target == None:
            return -1
        if source == target:
            return 0
        if target == "":
            return 0
        if len(target) > len(source):
            return -1

        for i in range(len(source)):
            res = i
            for char in target:
                if i >= len(source):
                    return -1
                if char == source[i]:
                    i += 1
                else:
                    break
                if i == res + len(target):
                    return res
        return -1


