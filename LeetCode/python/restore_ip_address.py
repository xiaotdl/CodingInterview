"""
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
"""


class Solution(object):
    def restoreIpAddresses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        result = []
        if s is None:
            return result
        # min ip len ~ max ip len
        if len(s) < 4 or len(s) > 12:
            return result

        self.helper(s, 0, [], result)

        return result

    def helper(self, s, pos, curr_nums, result):
        if len(curr_nums) == 4:
            if pos != len(s):
                return
            ip = '.'.join(curr_nums)
            result.append(ip)
            return

        curr_num  = ''
        for i in range(pos, min(pos + 3, len(s))):
            curr_num += s[i]
            if self.is_valid(curr_num):
                curr_nums.append(curr_num)
                self.helper(s, i + 1, curr_nums, result)
                curr_nums.pop()

    def is_valid(self, num):
        if len(num) > 1 and num[0] == '0':
            return False
        if not 0 <= int(num) <= 255:
            return False
        return True
