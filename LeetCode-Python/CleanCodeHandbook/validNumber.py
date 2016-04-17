class Solution(object):
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # [one pass]
        # O(n) runtime, O(1) space
        if not s:
            return False

        sign = None
        e_sign = None
        dot = None
        exponent = None
        num_start = None
        e_start = None
        start_index = self._lstrip(s)
        end_index = self._rstrip(s)

        if start_index == -1:
            return False

        new_s = s[start_index: end_index + 1]
        for i, char in enumerate(new_s):
            if (not sign and not num_start and (char == '+' or char == '-')
                and self._left_to_digit_or_dot(new_s, i)):
                sign = char
                continue
            elif (not dot and not e_start and char == '.'
                and len(new_s) != 1
                and self._next_to_digit(new_s, i)):
                dot = '.'
                num_start = True
                continue
            elif (not exponent and char == 'e'
                and self._surround_by_digit_or_dot_or_sign(new_s, i)):
                exponent = 'e'
                e_start = True
                continue
            elif (not e_sign and e_start and (char == '+' or char == '-')
                and self._left_to_digit_or_dot(new_s, i)):
                e_sign = char
                continue
            elif char.isdigit():
                num_start = True
                continue
            else:
                return False
        return True

    def _lstrip(self, s):
        for i in range(len(s)):
            if s[i].isspace():
                continue
            else:
                return i
        else:
            return -1

    def _rstrip(self, s):
        for i in range(len(s) - 1, -1, -1):
            if s[i].isspace():
                continue
            else:
                return i
        else:
            return -1

    def _next_to_digit(self, s, index):
        left = index - 1
        right = index + 1
        if left >= 0 and s[left].isdigit():
            return True
        if right <= len(s) - 1 and s[right].isdigit():
            return True
        return False

    def _next_to_e(self, s, index):
        left = index - 1
        right = index + 1
        if left >= 0 and s[left] == 'e':
            return True
        if right <= len(s) - 1 and s[right] == 'e':
            return True
        return False

    def _left_to_digit_or_dot(self, s, index):
        right = index + 1
        if right <= len(s) - 1 and (s[right].isdigit() or s[right] == '.'):
            return True
        return False

    def _surround_by_digit_or_dot_or_sign(self, s, index):
        left = index - 1
        right = index + 1
        if not (left >= 0 and (s[left].isdigit() or s[left] == '.')):
            return False
        if not (right <= len(s) - 1 and (s[right].isdigit() or s[right] in ('.', '-', '+'))):
            return False
        return True


print Solution().isNumber("46.e3")
print Solution().isNumber("2147483648")
print Solution().isNumber("-2147483648")
print Solution().isNumber("+-2")
print Solution().isNumber(" ")
# >>>
# True
# True
# True
# False
# False
