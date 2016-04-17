class Solution(object):
    def twoSum1(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # [binary search]
        # O(nlogn) runtime, O(1) space
        if not nums or target is None:
            return [-1, -1]

        for i in range(len(nums) - 1):
            j = self.bsearch(nums, target - nums[i], i)
            if j != -1:
                return [i, j]

        return [-1, -1]

    def bsearch(self, L, target, start):
        start = start
        end = len(L) - 1
        while start + 1 < end:
            mid = start + (end - start) / 2
            if L[mid] < target:
                start = mid
            elif L[mid] > target:
                end = mid
            else:
                return mid
        if L[start] == target:
            return start
        elif L[end] == target:
            return end
        else:
            return -1

    def twoSum2(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # [two pointers]
        # O(n) runtime, O(1) space
        if not nums or target is None:
            return [-1, -1]

        i = 0
        j = len(nums) - 1
        while i < j:
            if nums[i] + nums[j] > target:
                j -= 1
            elif nums[i] + nums[j] < target:
                i += 1
            else:
                return [i, j]

        return [-1, -1]


print Solution().twoSum1([2, 3, 4, 5, 6], 8)
print Solution().twoSum2([2, 3, 4, 5, 6], 8)
# >>>
# [0, 4]
# [0, 4]
