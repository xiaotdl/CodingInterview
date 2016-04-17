# Given two sorted integer arrays A and B, merge B into A as one sorted array.

# Note:
# You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.

class Solution:
    # @param A  a list of integers
    # @param m  an integer, length of A
    # @param B  a list of integers
    # @param n  an integer, length of B
    # @return nothing
    def merge_1(self, A, m, B, n):
        # move the biggest elem from A and B to the end of A
        # replace the left elem of B to front of A, if B has some remaining elem
        i = m - 1
        j = n - 1
        k = m + n - 1
        while i != -1 and j != -1:
            if A[i] >= B[j]:
                A[k] = A[i]
                i -= 1
            else:
                A[k] = B[j]
                j -= 1
            k -= 1
        if j != -1:
            A[:j + 1] = B[:j + 1]