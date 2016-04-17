# Follow up for "Remove Duplicates":
# What if duplicates are allowed at most twice?

# For example,
# Given sorted array A = [1,1,1,2,2,3],

# Your function should return length = 5, and A is now [1,1,2,2,3].

class Solution:
    # @param A a list of integers
    # @return an integer
    def removeDuplicates_1(self, A):
        if len(A) < 3: return len(A)
        i, front, count = 1, 1, 1
        while i < len(A):
            if A[i] == A[i - 1]:
                count += 1
            else:
                count = 1
            if count <= 2:
                A[front] = A[i]
                front += 1
            i += 1
        return front
