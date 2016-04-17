# Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

# For example,
# Given n = 3,

# You should return the following matrix:
# [
#  [ 1, 2, 3 ],
#  [ 8, 9, 4 ],
#  [ 7, 6, 5 ]
# ]

class Solution:
    # @return a list of lists of integer
    def generateMatrix_1(self, n):
        if n == 0: return []
        if n == 1: return [[1]]
        matrix = [[0]*n for i in range(n)]
        top, left = 0, 0
        bottom, right = n - 1, n - 1
        # 0->right, 1->down, 2->left, 3->up
        direction = 0
        num = 1
        while True:
            if direction == 0:
                for i in xrange(left, right + 1):
                    matrix[top][i] = num
                    num += 1
                top += 1
            if direction == 1:
                for i in xrange(top, bottom + 1):
                    matrix[i][right] = num
                    num += 1
                right -= 1
            if direction == 2:
                for i in xrange(right, left - 1, -1):
                    matrix[bottom][i] = num
                    num += 1
                bottom -= 1
            if direction == 3:
                for i in xrange(bottom, top - 1, -1):
                    matrix[i][left] = num
                    num += 1
                left += 1
            if num == n**2 + 1: return matrix
            direction = (direction + 1) % 4 