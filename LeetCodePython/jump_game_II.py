# Given an array of non-negative integers, you are initially positioned at the first index of the array.

# Each element in the array represents your maximum jump length at that position.

# Your goal is to reach the last index in the minimum number of jumps.

# For example:
# Given array A = [2,3,1,1,4]

# The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

class Solution:
    # @param A, a list of integers
    # @return an integer
    def jump_1(self, A):
        # last: max reachable distance in res jumps
        # curr: max reachable distance in res+1 jumps
        res, last, curr = 0, 0, 0
        for pos in xrange(len(A)):
            # If current pos exceeds last max reachable distance:
            # update last max reachable distance and add one jump
            if pos > last:
                last = curr
                res += 1
            curr = max(curr, pos + A[pos])
        return res