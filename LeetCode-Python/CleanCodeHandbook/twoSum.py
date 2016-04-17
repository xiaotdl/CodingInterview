class Solution(object):
    def twoSum1(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # [hash table]
        # O(n) runtime, O(n) space
        if not nums or target is None:
            return [-1, -1]

        d = {}
        for i in range(len(nums)):
            num = nums[i]
            if target - num in d:
                return [d[target - num], i]
            d[num] = i

        return [-1, -1]

    def twoSum2(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # [brute force]
        # O(n^2) runtime, O(1) space
        if not nums or target is None:
            return [-1, -1]

        for i in range(len(nums) - 1):
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] == target:
                    return [i, j]

        return [-1, -1]

    def twoSum3(self, nums, target):
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


print Solution().twoSum1([3, 2, 4], 6)
print Solution().twoSum2([3, 2, 4], 6)
print Solution().twoSum3([2, 3, 4, 5, 6], 8)
# >>>
# [1, 2]
# [1, 2]
# [0, 4]
