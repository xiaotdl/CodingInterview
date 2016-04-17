"""
Given an array of strings, return all groups of strings that are anagrams.

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Note
All inputs will be in lower-case
"""


class Solution:
    # @param strs: A list of strings
    # @return: A list of strings
    def anagrams(self, strs):
        # O(n), O(n)
        d = dict()
        for str in strs:
            sorted_str = ''.join(sorted(str))
            d[sorted_str] = [str] if sorted_str not in d else d[sorted_str] + [str]
        res = []
        for str in d:
            if len(d[str]) >= 2:
                res += d[str]
        return res
