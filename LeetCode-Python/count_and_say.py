# The count-and-say sequence is the sequence of integers beginning as follows:
# 1, 11, 21, 1211, 111221, ...

# 1 is read off as "one 1" or 11.
# 11 is read off as "two 1s" or 21.
# 21 is read off as "one 2, then one 1" or 1211.
# Given an integer n, generate the nth sequence.

# Note: The sequence of integers will be represented as a string.

# n == 1: 1
# n == 2: 11
# n == 3: 21
# n == 4: 1211

class Solution:
    # @return a string
    def countAndSay_1_iterative(self, n):
        # base result for n=1
        res = "1"
        # iterate till getting result for n=n
        for i in xrange(n - 1):
            tmp_res = ""
            prev = res[0]
            count = 1
            # Start comparing with next char
            # Update the count for same chars
            # Otherwise store curr res, reset char and count
            for curr in res[1:]:
                if curr == prev:
                    count  += 1
                else:
                    tmp_res += str(count) + prev
                    prev = curr
                    count = 1
            tmp_res += str(count) + prev
            res = tmp_res
        return res