# Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

# Note: You may not slant the container.

class Solution:
    # @return an integer
    def maxArea_1(self, height):
        max_area = 0
        curr_area = 0
        left = 0
        right = len(height) - 1
        while left < right:
            curr_area = min(height[left], height[right])*(right - left)
            if curr_area > max_area:
                max_area = curr_area
            if height[left] > height[right]:
                right -= 1
            else:
                left += 1
        return max_area


    def maxArea_2(self, height):
        max_area = 0
        curr_area = 0
        left = 0
        right = len(height) - 1
        lh = height[0]
        rh = height[len(height) - 1]
        while left < right:
            curr_area = min(lh, rh)*(right - left)
            if curr_area > max_area:
                max_area = curr_area
            if lh < rh:
                while left < right and height[left] <= lh:
                    left += 1
                if left < right:
                    lh = height[left]
            else:
                while left < right and height[right] <= rh:
                    right -= 1
                if left < right:
                    rh = height[right]
        return max_area