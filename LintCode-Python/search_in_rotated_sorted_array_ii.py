class Solution:
    """
    @param A : an integer ratated sorted array and duplicates are allowed
    @param target : an integer to be searched
    @return : a boolean
    """
    def search(self, A, target):
        # binary search
        # time: O(n)
        # space: O(1)
        if not A or target is None:
            return False

        start = 0
        end = len(A) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if target == A[mid]:
                return True
            if target > A[end]:
                if target > A[mid] and A[mid] > A[end]:
                    start = mid
                else:
                    end = mid
            elif target < A[end]:
                if target < A[mid] and A[mid] < A[end]:
                    end = mid
                elif target < A[mid] and A[mid] == A[end]:
                    end -= 1
                else:
                    start = mid
            else:
                return True

        if A[start] == target:
            return True
        elif A[end] == target:
            return True
        else:
            return False


if __name__ == '__main__':
    print Solution().search([1, 1, 1, 1, 0, 1, 1], 0)  # O(n), worst case
    print Solution().search([2,2,2,3,1], 1)
    print Solution().search([9,5,6,7,8,9,9,9,9,9,9], 8)
    print Solution().search([9,5,6,7,8,9,9,9,9,9,9], 0)
# >>>
# True
# True
# True
# False
