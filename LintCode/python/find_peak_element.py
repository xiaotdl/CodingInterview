class Solution:
    #@param A: An integers list.
    #@return: return any of peek positions.
    def findPeak(self, A):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not A:
            return -1

        start = 0
        end = len(A) -1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if A[mid - 1] < A[mid] and A[mid] > A[mid + 1]:
                return mid
            elif A[mid - 1] > A[mid] and A[mid] < A[mid + 1]:
                start = mid
            elif A[mid - 1] < A[mid] and A[mid] < A[mid + 1]:
                start = mid
            else:
                end = mid

        if A[start] > A[end]:
            return start
        else:
            return end


if __name__ == '__main__':
    print Solution().findPeak([1, 2, 1, 3, 4, 5, 7, 6])
# >>>
# 6
