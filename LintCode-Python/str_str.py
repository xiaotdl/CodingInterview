"""
strstr (a.k.a find sub string), is a useful function in string operation.
You task is to implement this function.

For a given source string and a target string, you should output the "first" index(from 0) of target string in source string.

If target is not exist in source, just return -1.

Example
If source="source" and target="target", return -1.

If source="abcdabcdefg" and target="bcd", return 1.

Challenge
O(n) time.

Clarification
Do I need to implement KMP Algorithm in an interview?

- Not necessary. When this problem occurs in an interview, the interviewer just want to test your basic implementation ability.
"""


class Solution:
    def strStr(self, source, target):
        # intuitive, two loops
        # time:  O(mn)
        # space: O(1)
        if source is None or target is None:
            return -1
        if target == "":
            return 0

        for i in range(len(source)):
            for j in range(len(target)):
                if i >= len(source):
                    return -1 # index out of range, no more need for matching
                if source[i + j] != target[j]:
                    break # doesn't match
            else:
                return i

        return -1


if __name__ == '__main__':
    print Solution().strStr('abcd', 'bc')
# >>>
# 1
