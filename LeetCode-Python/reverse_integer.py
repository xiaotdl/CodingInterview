# Reverse digits of an integer.

# Example1: x = 123, return 321
# Example2: x = -123, return -321

class Solution:
    # @return an integer
    def reverse_1(self, x):
        if x < 0:
            return (-1)*self.reverse(-x)

        result = 0
        while x > 0:
            result = result*10 + x%10
            x = x/10
        return result

    def reverse_2(self, x):
        result = 0
        sign = 1 if x > 0 else -1
        x = abs(x)
        while x > 0:
            result = result*10 + x%10
            x = x/10
        return sign*result