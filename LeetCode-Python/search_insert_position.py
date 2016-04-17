# Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

# You may assume no duplicates in the array.

# Here are few examples.
# [1,3,5,6], 5 → 2
# [1,3,5,6], 2 → 1
# [1,3,5,6], 7 → 4
# [1,3,5,6], 0 → 0

class Solution:
    # @param A, a list of integers
    # @param target, an integer to be inserted
    # @return integer
    def searchInsert_1(self, A, target):
        # time complexity: O(N)
        # intuitive
        for i in range(0, len(A)):
            if A[i] < target:
                continue
            if A[i] >= target:
                return i
        return len(A)


    def searchInsert_2(self, A, target):
        # time complexity: O(log N)
        # binary search
        start = 0
        end = len(A) - 1
        while start <= end:
            mid = (start + end) / 2
            if A[mid] == target:
                return mid
            if A[mid] > target:
                end = mid - 1
            else:
                start = mid + 1
        return mid if A[mid] > target else mid + 1