# Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

# The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

class Solution:
    # @return a boolean
    def isValid_1(self, s):
        valid_parentheses = {
            '(': ')', 
            '[': ']',
            '{': '}'
        }
        stack = []
        for i in xrange(len(s)):
            if s[i] in valid_parentheses.keys():
                stack.append(s[i])
            if s[i] in valid_parentheses.values():
                if stack == [] or stack.pop() != dict((v,k) for k,v in valid_parentheses.items()).get(s[i]):
                    return False
        return stack == []