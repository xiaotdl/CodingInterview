"""
Compare two strings A and B, determine whether A contains all of the characters in B.

The characters in string A and B are all Upper Case letters.

Example
For A = "ABCD", B = "ABC", return true.

For A = "ABCD", B = "AABC", return false.
"""


class Solution:
    """
    @param A : A string includes Upper Case letters
    @param B : A string includes Upper Case letters
    @return :  if string A contains all of the characters in B return True else return False
    """
    def compareStrings(self, A, B):
        # O(n), O(1)
        cnt = [0] * 256
        for char in A:
            cnt[ord(char)] += 1
        for char in B:
            cnt[ord(char)] -= 1
        for num in cnt:
            if num < 0:
                return False
        return True
