class Solution:
    # @param s: a list of char
    # @param offset: an integer 
    # @return: nothing
    def rotateString(self, chars, offset):
        # 3 step rotation
        # time:  O(n)
        # space: O(n), can't do in place in python as str is immutable
        if not chars or offset is None:
            return

        offset %= len(chars)
        self.reverse_list(chars, len(chars) - offset, len(chars) - 1)
        self.reverse_list(chars, 0, len(chars) - offset - 1)
        self.reverse_list(chars, 0, len(chars) - 1)

    def reverse_list(self, chars, start, end):
        for i in range((end - start + 1) / 2):
            chars[start + i], chars[end - i] = chars[end - i], chars[start + i]


if __name__ == '__main__':
    s = 'abcdefg'
    chars = list(s)
    Solution().rotateString(chars, 1)
    print chars
# >>>
# ['g', 'a', 'b', 'c', 'd', 'e', 'f']
