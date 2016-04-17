# Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

# You have the following 3 operations permitted on a word:

# a) Insert a character
# b) Delete a character
# c) Replace a character

# Ref - http://chaoren.is-programmer.com/posts/44402.html
# Image - http://en.wikipedia.org/wiki/Levenshtein_distance
# Initialization: 
# dp[i][0] = i (0 <= i <= word1 length), dp[0][j] = j (0 <= j <= word2 length).
# Dynamic programming:
# Let dp[i][j] indicate the steps (edit distance) of changing word1's first i letters to word2's first j letters. If word1's ith letter equals to word2's jth letter, then dp[i][j] = dp[i-1][j-1]. If not, 3 cases:
# 1) Change word1's first i-1 letters to word2's j-1 letters, then change word1's ith letter to word2's jth letter, i.e. dp[i-1][j-1] + 1
# 2) Change word1's first i letters to word2's first j-1 letters, then add word2's jth letter, i.e. dp[i][j-1] + 1
# 3) Delete word1's ith letter, then change word1's first i-1 letters to word2's first j letters, i.e. 1 + dp[i-1][j]
# dp[i][j] is the min value of the above 3 cases. 

class Solution:
    # @return an integer
    def minDistance_1_dp(self, word1, word2):
        # two dimension dp
        len1 = len(word1)
        len2 = len(word2)
        # initialization
        dp = [[0 for j in xrange(len2 + 1)] for i in xrange(len1 + 1)]
        for i in xrange(len1 + 1):
            dp[i][0] = i
        for j in xrange(len2 + 1):
            dp[0][j] = j
        # dp
        for i in xrange(1, len1 + 1):
            for j in xrange(1, len2 + 1):
                dp[i][j] = dp[i - 1][j - 1] if word1[i - 1] == word2[j - 1] else min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
        return dp[len1][len2]