# Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

# For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
# the contiguous subarray [4,−1,2,1] has the largest sum = 6.

# click to show more practice.

# More practice:
# If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

class Solution:
    # @param A, a list of integers
    # @return an integer
    def maxSubArray_1(self, nums):
        # time complexity: O(n)
        # space complexity: O(1)
        if len(nums) == 0:
            return
        cursum, maxsum = 0, nums[0]
        for i in nums:
            cursum = max(i, i+cursum)
            maxsum = max(maxsum, cursum)
        return maxsum
