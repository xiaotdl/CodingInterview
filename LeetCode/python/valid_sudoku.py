# Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

# The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

# Image portal: https://oj.leetcode.com/problems/valid-sudoku/

# A partially filled sudoku which is valid.

# Note:
# A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

class Solution:
    # @param board, a 9x9 2D array
    # @return a boolean
    def isValidSudoku_1(self, board):

        def check_valid(num, valid_num, dup):
            if num == '.':
                return True
            if num in valid_num and num not in dup:
                dup.append(num)
                return True
            return False

        valid_num = [str(n + 1) for n in xrange(9)]
        for row in board:
            dup = []
            for num in row:
                if check_valid(num, valid_num, dup) == False:
                    return False
        for i in xrange(9):
            dup = []
            for j in xrange(9):
                num = board[j][i]
                if check_valid(num, valid_num, dup) == False:
                    return False
        for i in [0, 3, 6]:
            for j in [0, 3, 6]:
                dup = []
                for x in xrange(3):
                    for y in xrange(3):
                        num = board[i + x][j + y]
                        if check_valid(num, valid_num, dup) == False:
                            return False
        return True