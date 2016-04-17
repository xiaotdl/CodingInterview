# Suppose a sorted array is rotated at some pivot unknown to you beforehand.

# (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

# You are given a target value to search. If found in the array return its index, otherwise return -1.

# You may assume no duplicate exists in the array.

class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return an integer
    def search_1(self, A, target):
        if not A: return -1
        l, r = 0, len(A) - 1
        while l <= r:
            m = (l + r) / 2
            if A[m] == target:
                return m
            if A[m] >= A[l]:
                if A[l] <= target and target < A[m]:
                    r = m - 1
                else:
                    l = m + 1
            else:
                if A[m] < target and target <= A[r]:
                    l = m + 1
                else:
                    r = m - 1
        return -1