class Solution:
    """
    @param A : a list of integers
    @param target : an integer to be searched
    @return : a list of length 2, [index1, index2]
    """
    def searchRange(self, A, target):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not A or target is None:
            return [-1, -1]

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
            return [-1, -1]

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
            return [-1, -1]

        return [left_index, right_index]


if __name__ == '__main__':
    print Solution().searchRange([5, 7, 7, 8, 8, 10], 8)
# >>>
# [3, 4]
