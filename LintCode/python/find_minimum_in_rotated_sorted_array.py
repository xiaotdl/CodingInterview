class Solution:
    # @param num: a rotated sorted array
    # @return: the minimum number in the array
    def findMin(self, num):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not num:
            return -1

        start = 0
        end = len(num) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if num[mid] > num[end]:
                start = mid
            else:
                end = mid

        return min(num[start], num[end])


if __name__ == '__main__':
    print Solution().findMin([4, 5, 6, 7, 0, 1, 2])
# >>>
# 0
