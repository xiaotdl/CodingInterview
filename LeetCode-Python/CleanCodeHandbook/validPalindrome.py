class Solution(object):
    def isPalindrome1(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # [intuitive]
        # O(n) runtime, O(n) space
        if s is None:
            return False
        if s == '':
            return True
        s = self.filter_str(s)
        for i in range(len(s) / 2):
            if s[i] != s[len(s) - 1 - i]:
                return False

        return True

    def filter_str(self, s):
        new_s = ''
        for char in s:
            if char.isalnum():
                new_s += char
        return new_s.lower()

    def isPalindrome2(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # [two pointers]
        # O(n) runtime, O(1) space
        if s is None:
            return False
        if s == '':
            return True

        i = 0
        j = len(s) - 1
        while i < j:
            while i < j and not s[i].isalnum():
                i += 1
            while i < j and not s[j].isalnum():
                j -= 1
            if s[i].lower() != s[j].lower():
                return False
            i += 1
            j -= 1

        return True


print Solution().isPalindrome1("A man, a plan, a canal: Panama")
print Solution().isPalindrome1("a  b cba")
print Solution().isPalindrome1("a.")
print Solution().isPalindrome1("a.b")
print Solution().isPalindrome2("A man, a plan, a canal: Panama")
print Solution().isPalindrome2("a  b cba")
print Solution().isPalindrome1("a.")
print Solution().isPalindrome1("a.b")
# >>>
# True
# True
# True
# False
# True
# True
# True
# False
