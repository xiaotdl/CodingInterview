# Given an array and a value, remove all instances of that value in place and return the new length.

# The order of elements can be changed. It doesn't matter what you leave beyond the new length.

class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    def removeElement(self, A, elem):
        # move all elements not equal to elem to front of the list
        length = 0
        for i in A:
            if i != elem:
                A[length] = i
                length += 1
        return length