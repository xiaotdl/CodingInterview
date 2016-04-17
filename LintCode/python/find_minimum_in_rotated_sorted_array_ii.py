class Solution:
    # @param num: a rotated sorted array
    # @return: the minimum number in the array
    def findMin(self, num):
        # binary search
        # time:  O(n)
        # space: O(1)
        if not num:
            return -1

        start = 0
        end = len(num) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if num[mid] > num[end]:
                start = mid
            elif num[mid] < num[end]:
                end = mid
            else:
                end -= 1

        return min(num[start], num[end])


if __name__ == '__main__':
    print Solution().findMin([4, 5, 6, 7, 0, 1, 2])  # O(logn)
    print Solution().findMin([1, 1, 1, 1, 0, 1, 1])  # O(n), worst case
# >>>
# 0
# 0
