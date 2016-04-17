# Given a roman numeral, convert it to an integer.

# Input is guaranteed to be within the range from 1 to 3999.

class Solution:
    # @return an integer
    def romanToInt_1(self, s):
        # I -> 1     V -> 5
        # X -> 10    L -> 50
        # C -> 100   D -> 500
        # M -> 1000
        d = {'I':1, 'V':5, 'X':10, 'L':50,\
             'C':100, 'D':500, 'M':1000}
        l = [d[x] for x in list(s)]
        total = 0
        for i in range(0, len(l)-1):
            if l[i] < l[i + 1]:
                total -= l[i]
            else:
                total += l[i]
        return total + l[-1]