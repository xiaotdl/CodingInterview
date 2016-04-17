# Given an integer, convert it to a roman numeral.

# Input is guaranteed to be within the range from 1 to 3999.

class Solution:
    # @return a string
    def intToRoman_1(self, num):
        roman_numeral = ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"];
        value         = [1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1 ];
        str = ""
        for i in range(0, len(value)):
            if num == 0:    break
            while num >= value[i]:
                num -= value[i]
                str += roman_numeral[i]
        return str