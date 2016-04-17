class Solution(object):
    maxDiv10 = (2**31 - 1) / 10
    def atoi(self, str):
        """
        :type str: str
        :rtype: int
        """
        # [one pass]
        # O(n) runtime, O(1) space
        sign = None
        num = 0
        start_index = self._lstrip(str)
        for char in str[start_index:]:
            if char == '+':
                if not sign:
                    sign = 1
                else:
                    break
            elif char == '-':
                if not sign:
                    sign = -1
                else:
                    break
                continue
            elif char.isdigit():
                digit = int(char)
                if num > self.maxDiv10 or (num == self.maxDiv10 and digit >= 8):
                    return 2**31 - 1 if not sign or sign > 0 else -2**31
                num = num * 10 + digit
            else:
                break
        sign = 1 if not sign else sign
        return sign * num

    def _lstrip(self, s):
        for i in range(len(s)):
            if s[i].isspace():
                continue
            else:
                return i



print Solution().atoi("2147483648")
print Solution().atoi("-2147483648")
print Solution().atoi("+-2")
# >>>
# 2147483647
# -2147483648
