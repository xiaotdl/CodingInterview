# Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

# You may assume that the array is non-empty and the majority element always exist in the array.

class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def majorityElement_1(self, nums):
        # time complexity: O(n)
        # space complexity: O(n)
        cnt = dict()
        for i in nums:
            if i not in cnt:
                cnt[i] = 1
            else:
                cnt[i] += 1
            if cnt[i] > len(nums)/2:
                return i


    def majorityElement_2(self, nums):
        # time complexity: O(nlogn)
        # space complexity: O(1)
        nums = sorted(nums)
        return nums[len(nums)/2]


    def majorityElement_3(self, nums):
        # time complexity: O(n)
        # space complexity: O(1)
        cnt = 0
        for i in range(len(nums)):
            if cnt == 0:
                major = nums[i]
            if nums[i] != major:
                cnt -= 1
            else:
                cnt += 1
        return major


