class Solution:
    """
    @param A : a list of integers
    @param target : an integer to be inserted
    @return : an integer
    """
    def searchInsert(self, A, target):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if A is None or target is None:
            return -1
        if len(A) == 0:
            return 0
        if target <= A[0]:
            return 0
        if target > A[-1]:
            return len(A)

        # find the index of the first number that's greater than or equal to target
        start = 0
        end = len(A) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2

            if A[mid] < target:
                start = mid
            elif A[mid] > target:
                end = mid
            else:
                return mid

        if A[start] == target:
            return start
        elif A[end] == target:
            return end
        else:
            return end


if __name__ == '__main__':
    print Solution().searchInsert([1,3,5,6], 5)
    print Solution().searchInsert([1,3,5,6], 2)
    print Solution().searchInsert([1,3,5,6], 7)
    print Solution().searchInsert([1,3,5,6], 0)
# >>>
# 2
# 1
# 4
# 0
