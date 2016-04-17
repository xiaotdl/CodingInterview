# Determine whether an integer is a palindrome. Do this without extra space.

# click to show spoilers.

# Some hints:
# Could negative integers be palindromes? (ie, -1)

# If you are thinking of converting the integer to string, note the restriction of using extra space.

# You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

# There is a more generic way of solving this problem.

class Solution:
    # @return a boolean
    def isPalindrome_1(self, x):
        if x < 0:
            return False
        original_x = x
        reversed_x = 0
        while x > 0:
            reversed_x = reversed_x * 10 + x % 10
            x /= 10
        return original_x == reversed_x