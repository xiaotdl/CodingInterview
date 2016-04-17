class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s):
        """
        :type s: str
        :rtype: int
        """
        # [one pass]
        # O(n) runtime, O(1) space
        # maintain a sliding window (left, right)
        # use a ptr to record the pos of last occurrence of left char
        if not s:
            return 0

        left = 0
        pos = -1 # records the position of last occurence of first char in substr
        max_len = 0
        print 'l', 'r', ' p', 'max', 'loop'
        for right in range(1, len(s)):
            print left, right, '%2d'%pos, max_len, 'in'
            if s[right] == s[right - 1]:
                continue
            if pos >= 0 and s[pos] != s[right]:
                max_len = max(right - left, max_len)
                left = pos + 1
            pos = right - 1
            print left, right, '%2d'%pos, max_len, 'out'

        return max(len(s) - left, max_len)

    def lengthOfLongestSubstringKDistinct1(self, s, k):
        """
        :type s: str
        :type k: str
        :rtype: int
        """
        # [one pass]
        # O(n) runtime, O(n) space
        if not s or not k:
            return 0

        count = [0 for i in range(256)]
        i = 0
        numDistinct = 0
        max_len = 0
        for j in range(len(s)):
            if count[ord(s[j])] == 0:
                numDistinct += 1
            count[ord(s[j])] += 1
            while numDistinct > k:
                count[ord(s[i])] -= 1
                if count[ord(s[i])] == 0:
                    numDistinct -= 1
                i += 1
            max_len = max(j - i + 1, max_len)

        return max_len

    def lengthOfLongestSubstringKDistinct2(self, s, k):
        """
        :type s: str
        :type k: str
        :rtype: int
        """
        # [one pass]
        # O(n) runtime, O(1) space
        if not s or not k:
            return 0

        max_len = 1
        start = 0
        map = dict()
        map[s[0]] = 1
        for end in range(1, len(s)):
            char = s[end]
            if char in map:
                map[char] += 1
            else:
                if len(map) == k:
                    max_len = max(end - start, max_len)
                    for i in range(start, end):
                        map[s[i]] -= 1
                        if map[s[i]] == 0:
                            start = i + 1
                            del map[s[i]]
                            break
                map[char] = 1
        max_len = max(len(s) - start, max_len)

        return max_len


print Solution().lengthOfLongestSubstringTwoDistinct("eeeeeceba")
print Solution().lengthOfLongestSubstringKDistinct1("eceba", 3)
print Solution().lengthOfLongestSubstringKDistinct2("eceba", 3)
# >>>
# l r  p max loop
# 0 1 -1 0 in
# 0 2 -1 0 in
# 0 3 -1 0 in
# 0 4 -1 0 in
# 0 5 -1 0 in
# 0 5  4 0 out
# 0 6  4 0 in
# 0 6  5 0 out
# 0 7  5 0 in
# 6 7  6 7 out
# 6 8  6 7 in
# 7 8  7 7 out
# 7
