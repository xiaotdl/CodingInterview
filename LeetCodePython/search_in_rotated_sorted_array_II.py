# Follow up for "Search in Rotated Sorted Array":
# What if duplicates are allowed?

# Would this affect the run-time complexity? How and why?

# Write a function to determine if a given target is in the array.

class Solution:
    # @param A a list of integers
    # @param target an integer
    # @return a boolean
    def search_1(self, A, target):
        if not A: return False
        l, r = 0, len(A) - 1
        while l <= r:
            m = (l + r) / 2
            if A[m] == target:
                return True
            if A[m] > A[l]:
                if A[l] <= target and target < A[m]:
                    r = m - 1
                else:
                    l = m + 1
            elif A[m] < A[l]:
                if A[m] < target and target <= A[r]:
                    l = m + 1
                else:
                    r = m - 1
            else:
                l += 1
        return False