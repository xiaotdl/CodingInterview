class Solution:
    # @param digits, a list of integer digits
    # @return a list of integer digits
    def plusOne_1(self, digits):
        if digits == [0]: return [1]
        num = 0
        j = 1
        for i in reversed(digits):
            num += i*j
            j *= 10
        num += 1
        result = []
        while num > 0:
            result.append(num%10)
            num /= 10
        return result[::-1]