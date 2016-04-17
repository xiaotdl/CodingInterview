class Solution:
    """
    @param matrix: An list of lists of integers
    @param target: An integer you want to search in matrix
    @return: An integer indicates the total occurrence of target in the given matrix
    """
    def searchMatrix(self, matrix, target):
        # intuitive
        # time:  O(m+n)
        # space: O(1)
        if not matrix or not matrix[0] or target is None:
            return 0

        # rule: start from bottom left (bl)
        #         if target <= bl, go up from bottom to up in column
        #         elif target > bl, go right

        occur = 0
        i = len(matrix) - 1 # start row
        j = 0               # start column
        while i >= 0 and j <= len(matrix[0]) - 1:
            if matrix[i][j] > target:
                i -= 1
            elif matrix[i][j] == target:
                i -= 1
                occur += 1
            else:
                j += 1

        return occur


if __name__ == '__main__':
    matrix = [
               [1, 3, 5, 7],
               [2, 4, 7, 8],
               [3, 5, 9, 10]
             ]
    print Solution().searchMatrix(matrix, 3)
# >>>
# 2
