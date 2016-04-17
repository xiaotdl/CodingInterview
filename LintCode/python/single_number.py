"""
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4

Challenge
One-pass, constant extra space.
"""


class Solution:
    """
    @param A : an integer array
    @return : a integer
    """
    def singleNumber(self, A):
        # O(n), O(1)
        if not A:
            return 0
        res = A[0]
        for i in range(1, len(A)):
            res ^= A[i]
        return res
