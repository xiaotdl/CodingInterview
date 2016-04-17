class Solution:
    """
    @param nums: The rotated sorted array
    @return: nothing
    """
    def recoverRotatedSortedArray1(self, nums):
        # 3 step rotation
        # time:  O(n)
        # space: O(n)
        if not nums:
            return

        min_index = self.find_min_index(nums)

        step1 = nums[:min_index][::-1]
        step2 = nums[min_index:][::-1]
        step3 = (step1 + step2)[::-1]
        for i in range(len(nums)):
            nums[i] = step3[i]

    def recoverRotatedSortedArray2(self, nums):
        # 3 step rotation
        # time:  O(n)
        # space: O(1)
        if not nums:
            return

        min_index = self.find_min_index(nums)
        self.reverse_list(nums, 0, min_index - 1)
        self.reverse_list(nums, min_index, len(nums) - 1)
        self.reverse_list(nums, 0, len(nums) - 1)

    def reverse_list(self, nums, start, end):
        for i in range((end - start + 1) / 2):
            nums[start + i], nums[end - i] = nums[end - i], nums[start + i]

    def find_min_index(self, nums):
        # binary search is better
        min_index = 0
        min_num = nums[0]
        for i in range(len(nums)):
            if nums[i] < min_num:
                min_index = i
                min_num = nums[i]
        return min_index


if __name__ == '__main__':
    nums = [4, 5, 1, 2, 3]
    Solution().recoverRotatedSortedArray1(nums)
    print nums

    nums = [4, 5, 1, 2, 3]
    Solution().recoverRotatedSortedArray2(nums)
    print nums
# >>>
# [1, 2, 3, 4, 5]
# [1, 2, 3, 4, 5]
