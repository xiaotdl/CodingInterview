class Solution:
    # @param nums: The integer array
    # @param target: Target number to find
    # @return the first position of target in nums, position start from 0
    def binarySearch(self, nums, target):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not nums or target is None:
            return -1

        start = 0
        end = len(nums) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2

            if nums[mid] < target:
                start = mid
            elif nums[mid] > target:
                end = mid
            else:
                end = mid

        if nums[start] == target:
            return start
        elif nums[end] == target:
            return end
        else:
            return -1


if __name__ == '__main__':
    print Solution().binarySearch([1, 2, 3, 3, 4, 5, 10], 3)
# >>>
# 2
