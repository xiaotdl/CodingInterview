class Solution:
    # @param {int[]} A an integer array
    # @param {int} target an integer
    # @param {int} k a non-negative integer
    # @return {int[]} an integer array
    def kClosestNumbers(self, A, target, k):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not A or target is None or not k:
            return []
        if k > len(A):
            raise Exception('you are too greedy')

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

        closest = -1
        if abs(A[start] - target) <= abs(A[end] - target):
            closest = start
        else:
            closest = end

        result = [A[closest]]
        left = 1
        right = 1
        while k - 1 > 0:
            if closest - left < 0:
                result.append(A[closest + right])
                right += 1
            elif closest + right > len(A) - 1:
                result.append(A[closest - left])
                left += 1
            elif abs(A[closest - left] - target) <= abs(A[closest + right] - target):
                result.append(A[closest - left])
                left += 1
            else:
                result.append(A[closest + right])
                right += 1
            k -= 1

        return result


if __name__ == '__main__':
    print Solution().kClosestNumbers([1,2,3], 2, 0)
    print Solution().kClosestNumbers([1,4,6,10,20], 21, 4)
    print Solution().kClosestNumbers([1,4,8,12,16,28,38], 12, 4)
    print Solution().kClosestNumbers([1,2,3], 3, 4)
# >>>
# []
# [20, 10, 6, 4]
# [12, 8, 16, 4]
# Exception: you are too greedy

