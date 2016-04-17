class Solution:
    """
    @param A : a list of integers
    @param target : an integer to be searched
    @return : an integer
    """
    def search(self, A, target):
        # binary search
        # time: O(logn)
        # space: O(1)
        if not A or target is None:
            return -1

        start = 0
        end = len(A) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if target == A[mid]:
                return mid
            if target > A[end]:
                if target > A[mid] and A[mid] > A[end]:
                    start = mid
                else:
                    end = mid
            elif target < A[end]:
                if target < A[mid] and A[mid] <= A[end]:
                    end = mid
                else:
                    start = mid
            else:
                return end

        if A[start] == target:
            return start
        elif A[end] == target:
            return end
        else:
            return -1


if __name__ == '__main__':
    print Solution().search([4, 5, 1, 2, 3], 1)
    print Solution().search([4, 5, 1, 2, 3], 0)
    print Solution().search([6, 8, 9, 1, 3, 5], 5)
# >>>
# 2
# -1
# 5
