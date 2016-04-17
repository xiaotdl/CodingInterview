# Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

# For example, 
# Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

# Image portal: https://oj.leetcode.com/problems/trapping-rain-water/

# The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

class Solution:
    # @param A, a list of integers
    # @return an integer
    def trap_1(self, A):
        if not A:
            return 0

        highest_left_on_left = []
        highest_right_on_right = []
        
        for i in xrange(len(A)):
            highest_left_on_left.append(A[i] if i == 0 else max(A[i], highest_left_on_left[-1]))

        for i in xrange(len(A) - 1, -1, -1):
            highest_right_on_right.insert(0, A[i] if i == len(A) - 1 else max(A[i], highest_right_on_right[0]))
        
        res = 0
        for i, curr_height in enumerate(A):
            min_side_height = min(highest_left_on_left[i], highest_right_on_right[i])
            if min_side_height > curr_height:
                res += (min_side_height - curr_height) * 1
        return res
