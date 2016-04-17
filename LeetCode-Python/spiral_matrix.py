# Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

# For example,
# Given the following matrix:

# [
#  [ 1, 2, 3 ],
#  [ 4, 5, 6 ],
#  [ 7, 8, 9 ]
# ]
# You should return [1,2,3,6,9,8,7,4,5].

class Solution:
    # @param matrix, a list of lists of integers
    # @return a list of integers
    def spiralOrder(self, matrix):
        if matrix == []: return []
        top, left = 0, 0
        bottom, right = len(matrix) - 1, len(matrix[0]) - 1
        # 0->right, 1->down, 2->left, 3->up
        direction = 0
        res = []
        while True:
            if direction == 0:
                for i in xrange(left, right + 1)：
                    res.append(matrix[top][i])
                top += 1
            if direction == 1:
                for i in xrange(top, bottom + 1):
                    res.append(matrix[i][bottom])
                right -= 1
            if direction == 2:
                for i in xrange(right, left - 1, -1):
                    res.append(matrix[bottom][i])
                bottom -= 1
            if direction == 3:
                for i in xrange(bottom, top - 1, -1):
                    res.append(matrix[i][left])
                left += 1
            if top > bottom or left > right: return res
            direction = (direction + 1) % 4