# Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

# click to show follow up.

# Follow up:
# Did you use extra space?
# A straight forward solution using O(mn) space is probably a bad idea.
# A simple improvement uses O(m + n) space, but still not the best solution.
# Could you devise a constant space solution?

class Solution:
    # @param matrix, a list of lists of integers
    # RETURN NOTHING, MODIFY matrix IN PLACE.
    def setZeroes_1(self, matrix):
        # O(mn), O(m+n)
        row_num, col_num = len(matrix), len(matrix[0])
        row_to_zeros, col_to_zeros = set(), set()
        for i in xrange(row_num):
            for j in xrange(col_num):
                if matrix[i][j] == 0:
                    row_to_zeros.add(i)
                    col_to_zeros.add(j)
        for row in row_to_zeros:
            for i in xrange(col_num):
                matrix[row][i] = 0
        for col in col_to_zeros:
            for i in xrange(row_num):
                matrix[i][col] = 0


    def setZeroes_2(self, matrix):
        # O(mn), O(1)
        row, col = 1, 1
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[0][j] == 0:
                    row = 0
                if matrix[i][0] == 0:
                    col = 0
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == 0:
                    matrix[i][0], matrix[0][j] = 0, 0
        # set matrix elements to 0 if corresponding first row||column element is 0
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    if i != 0 and j != 0:
                        matrix[i][j] = 0
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if row == 0:
                    matrix[0][j] = 0
                if col == 0:
                    matrix[i][0] = 0
