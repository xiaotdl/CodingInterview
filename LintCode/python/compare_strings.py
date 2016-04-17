"""
Write a method anagram(s,t) to decide if two strings are anagrams or not.

Example
Given s="abcd", t="dcab", return true.

Challenge
O(n) time, O(1) extra space
"""


class Solution:
    """
    @param s: The first string
    @param b: The second string
    @return true or false
    """
    def anagram(self, s, t):
        # O(logn), O(n)
        return sorted(s) == sorted(t)


    def anagram_(self, s, t):
        # O(n), O(1)
        if len(s) != len(t):
            return False
        cnt = [0] * 256
        for char in s:
            cnt[ord(char)] += 1
        for char in t:
            cnt[ord(char)] -= 1
        for num in cnt:
            if num != 0:
                return False
        return True
