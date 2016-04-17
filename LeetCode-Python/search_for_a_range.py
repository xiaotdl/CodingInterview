# Given a sorted array of integers, find the starting and ending position of a given target value.

# Your algorithm's runtime complexity must be in the order of O(log n).

# If the target is not found in the array, return [-1, -1].

# For example,
# Given [5, 7, 7, 8, 8, 10] and target value 8,
# return [3, 4].

class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return a list of length 2, [index1, index2]
    def searchRange_1(self, A, target):
        if not A: return [-1, -1]
        start, end = 0, len(A) - 1
        while start <= end:
            mid = (start + end)/2
            if A[mid] == target:
                res = [0, 0]
                if A[start] == target: 
                    res[0] = start
                if A[end] == target: 
                    res[1] = end
                for i in xrange(mid, start - 1, -1):
                    if A[i] != target: 
                        res[0] = i + 1; break
                for i in xrange(mid, end + 1):
                    if A[i] != target: 
                        res[1] = i - 1; break
                return res
            elif A[mid] > target:
                end = mid - 1
            else:
                start = mid + 1
        return [-1, -1]
        