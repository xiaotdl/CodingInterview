class Solution:
    # @param {int[]} A an integer array sorted in ascending order
    # @param {int} target an integer
    # @return {int} an integer
    def totalOccurrence(self, A, target):
        # binary search
        # time: O(logn)
        # space: O(1)
        if not A or target is None:
            return 0

        start = 0
        end = len(A) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2

            if A[mid] < target:
                start = mid
            elif A[mid] > target:
                end = mid
            else:
                end = mid

        if A[start] == target:
            left_index = start
        elif A[end] == target:
            left_index = end
        else:
            return 0

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
            right_index = end
        elif A[start] == target:
            right_index = start
        else:
            return 0

        return right_index - left_index + 1


if __name__ == '__main__':
    print Solution().totalOccurrence([1, 3, 3, 4, 5], 3)
# >>>
# 2
