class Solution:
    """
    @param nums: A list of Integers.
    @return: A list of permutations.
    """
    def permute(self, nums):
        # recursion
        # time:  O(n!)
        # space: O(n)
        if not nums:
            return []

        result = []
        self.helper(sorted(nums), [], result)
        return result

    def helper(self, nums, curr_list, result):
        if len(curr_list) == len(nums):
            result.append(curr_list[:])
            return # to save a few steps

        for i in range(len(nums)):
            if nums[i] in curr_list:
                continue

            curr_list.append(nums[i])
            self.helper(nums, curr_list, result)
            curr_list.pop()


if __name__ == '__main__':
    print Solution().permute([1,2,3])
# >>>
# [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

