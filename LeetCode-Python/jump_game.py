# Given an array of non-negative integers, you are initially positioned at the first index of the array.

# Each element in the array represents your maximum jump length at that position.

# Determine if you are able to reach the last index.

# For example:
# A = [2,3,1,1,4], return true.

# A = [3,2,1,0,4], return false.

class Solution:
    # @param A, a list of integers
    # @return a boolean
    def canJump_1_dp(self, A):
        # one dimension dp
        # current reachable length
        can_reach = 0
        # keep tracking current reachable length till it reaches the end (len(A)-1)
        for pos in xrange(len(A)):
            if pos <= can_reach:
                can_reach = max(can_reach, pos + A[pos])
                if can_reach >= len(A) - 1:
                    return True
        return False