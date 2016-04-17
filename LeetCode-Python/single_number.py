# Given an array of integers, every element appears twice except for one. Find that single one.

# Note:
# Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber_1(self, A):
    	# a number will stay the same after bitwise exclusive or with another number twice
    	# num ^ 0 = num
    	# num ^ 1 = ~num
        tmp = 0
        for num in A:
            tmp ^= num
        return tmp