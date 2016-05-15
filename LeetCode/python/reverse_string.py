class Solution(object):
    # time: O(n)
    # space: O(n), (if given a char array, it'll be O(1))
    def reverseString(self, s):
        """
        :type s: str
        :rtype: str
        """
        if not s:
            return ''
        chars = [char for char in s]
        for i in range(len(chars)/2):
            chars[i], chars[len(chars) - 1 - i] = chars[len(chars) - 1 - i], chars[i]
        return ''.join(chars)


