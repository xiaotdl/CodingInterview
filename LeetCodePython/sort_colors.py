# Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

# Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

# Note:
# You are not suppose to use the library's sort function for this problem.

# Follow up:
# A rather straight forward solution is a two-pass algorithm using counting sort.
# First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

# Could you come up with an one-pass algorithm using only constant space?

class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors_1(self, A):
        # intuitive
        count0 = count1 = count2 = 0
        for i in A:
            if i == 0:
                count0 += 1
            elif i == 1:
                count1 += 1
            else:
                count2 += 1
        for i in range(0, len(A)):
            if count0 > 0:
                A[i] = 0
                count0 -= 1
            elif count1 > 0:
                A[i] = 1
                count1 -= 1
            else:
                A[i] = 2
                count2 -=1