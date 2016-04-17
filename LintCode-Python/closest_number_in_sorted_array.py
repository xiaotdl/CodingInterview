class Solution:
    # @param {int[]} A an integer array sorted in ascending order
    # @param {int} target an integer
    # @return {int} an integer
    def closestNumber(self, A, target):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not A or target is None:
            return -1

        start = 0
        end = len(A) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if A[mid] > target:
                end = mid
            elif A[mid] < target:
                start = mid
            else:
                return mid

        if abs(A[start] - target) < abs(A[end] - target):
            return start
        else:
            return end

if __name__ == '__main__':
    print Solution().closestNumber([1, 4, 6, 8], 3)
# >>>
# 1
