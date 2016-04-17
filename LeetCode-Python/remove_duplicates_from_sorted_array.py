# Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

# Do not allocate extra space for another array, you must do this in place with constant memory.

# For example,
# Given input array A = [1,1,2],

# Your function should return length = 2, and A is now [1,2].

class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates_1(self, A):
        #move non duplicate number to front of the array
        if not A: return
        i, front = 1, 1
        while i < len(A):
            if A[i] != A[i-1]:
                A[front] = A[i]
                front += 1
            i += 1
        return front
