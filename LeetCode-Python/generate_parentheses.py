# Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

# For example, given n = 3, a solution set is:

# "((()))", "(()())", "(())()", "()(())", "()()()"

class Solution:
    # @param an integer
    # @return a list of string
    def generateParenthesis_1_recursive(self, n):
        result = []
        self.recursive_worker(n, n, "", result)
        return result

    def recursive_worker(self, left, right, s, l):
        # wrong output
        if left > right:
            return
        # valid output
        if left == 0 and right == 0:
            l.append(s)
            return
        # add the left right parenthesis
        if left == 0:
            self.recursive_worker(0, right - 1, s + ')', l)
            return
        self.recursive_worker(left - 1, right, s + '(', l)
        self.recursive_worker(left, right - 1, s + ')', l)