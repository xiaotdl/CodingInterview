class Solution:
    """
    @param nums: A list of integers.
    @return: A list of unique permutations.
    """
    def permuteUnique(self, nums):
        # recursion
        # time:  O(n!)
        # space: O(n)
        if not nums:
            return []

        result = []
        visited = [False for i in range(len(nums))]
        self.helper(sorted(nums), visited, [], result)
        return result

    def helper(self, nums, visited, curr_list, result):
        if len(curr_list) == len(nums):
            result.append(curr_list[:])
            return

        for i in range(len(nums)):
            # if visited[i] or (i != 0 and nums[i] == nums[i - 1] and not visited[i - 1]):
            if visited[i]:
                continue

            curr_list.append(nums[i])
            visited[i] = True
            self.helper(nums, visited, curr_list, result)
            curr_list.pop()
            visited[i] = False


if __name__ == '__main__':
    print Solution().permuteUnique([1,2,2])
# >>>
# [[1, 2, 2], [2, 1, 2], [2, 2, 1]]
