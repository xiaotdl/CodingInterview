class Solution:
    # @param A: The integer array
    # @param target: Target number to find
    # @return the first position of target in A, position start from 0
    def binarySearch(self, A, target):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not A or target is None:
            return -1

        start = 0
        end = len(A) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2

            if A[mid] < target:
                start = mid
            elif A[mid] > target:
                end = mid
            else:
                start = mid

        if A[end] == target:
            return end
        elif A[start] == target:
            return start
        else:
            return -1


if __name__ == '__main__':
    print Solution().binarySearch([1, 2, 3, 3, 4, 5, 10], 3)
# >>>
# 2
