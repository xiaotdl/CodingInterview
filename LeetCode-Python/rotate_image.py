# You are given an n x n 2D matrix representing an image.

# Rotate the image by 90 degrees (clockwise).

# Follow up:
# Could you do this in-place?

class Solution:
    # @param matrix, a list of lists of integers
    # @return a list of lists of integers
    def rotate_1(self, matrix):
        # step 1: flip it |
        # step 2: filp it /
        n = len(matrix)
        for row in matrix:
            for i in range(0, n/2):
                tmp = row[n - i - 1]
                row[len(row) - i - 1] = row[i]
                row[i] = tmp
        for i in range(0, n):
            for j in range(0, n):
                if i == n - 1 - j:
                    continue
                if j < n - 1 - i:
                    tmp = matrix[n - 1 - j][n - 1 - i]
                    matrix[n - 1 - j][n - 1 - i] = matrix[i][j]
                    matrix[i][j] = tmp
        return matrix