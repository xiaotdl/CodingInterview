class Solution(object):
    def reverseWords(self, s):
        """
        :type s: str
        :rtype: str
        """
        # [intuitive]
        # O(n) runtime, O(n) space
        if not s:
            return ''
        return ' '.join(reversed(s.split()))


print Solution().reverseWords('the sky is blue')
# >>>
# blue is sky the
