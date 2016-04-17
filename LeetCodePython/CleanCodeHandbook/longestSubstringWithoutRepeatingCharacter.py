class Solution(object):
    def lengthOfLongestSubstring1(self, s):
        """
        :type s: str
        :rtype: int
        """
        # [two pointers]
        # O(n) runtime, O(1) space
        if not s:
            return 0

        exist = dict() # max size = charset
        max_len = 0
        i = 0
        for j in range(len(s)):
            while exist.get(s[j]):
                exist[s[i]] = False
                i += 1
            exist[s[j]] = True
            max_len = max(j - i + 1, max_len)
        return max_len

    def lengthOfLongestSubstring2(self, s):
        """
        :type s: str
        :rtype: int
        """
        # [one pass]
        # O(n) runtime, O(1) space
        if not s:
            return 0

        charMap = [-1 for i in range(256)] # max size = charset
        max_len = 0
        i = 0
        for j in range(len(s)):
            pos = ord(s[j])
            if charMap[pos] >= i:
                i = charMap[pos] + 1
            charMap[pos] = j
            max_len = max(j - i + 1, max_len)
        return max_len


print Solution().lengthOfLongestSubstring1("aa")
print Solution().lengthOfLongestSubstring1("abba")
print Solution().lengthOfLongestSubstring1("abcdb123")

print Solution().lengthOfLongestSubstring2("aa")
print Solution().lengthOfLongestSubstring2("abba")
print Solution().lengthOfLongestSubstring2("abcdb123")
# >>>
# 2
# 6
