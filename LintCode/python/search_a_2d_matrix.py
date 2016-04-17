class Solution:
    """
    @param matrix, a list of lists of integers
    @param target, an integer
    @return a boolean, indicate whether matrix contains target
    """
    def searchMatrix(self, matrix, target):
        # binary search
        # time:  O(logmn)
        # space: O(1)
        if not matrix or target is None:
            return False

        # find the index of the first number that's smaller than or equal to target, in rows
        start = 0
        end = len(matrix) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2
            if matrix[mid][0] < target:
                start = mid
            elif matrix[mid][0] > target:
                end = mid
            else:
                return True

        if target < matrix[start][0]:
            return False
        elif matrix[start][0] == target:
            return True
        elif matrix[start][0] < target < matrix[end][0]:
            row_index = start
        elif matrix[end][0] == target:
            return True
        else:
            row_index = end

        # find the target in this row
        start = 0
        end = len(matrix[0]) - 1

        while start + 1 < end:
            mid = start + (end - start) / 2
            if matrix[row_index][mid] < target:
                start = mid
            elif matrix[row_index][mid] > target:
                end = mid
            else:
                return True

        if matrix[row_index][start] == target:
            return True
        elif matrix[row_index][end] == target:
            return True
        else:
            return False

if __name__ == '__main__':
    matrix = [
                [1,   3,  5,  7],
                [10, 11, 16, 20],
                [23, 30, 34, 50]
             ]
    print Solution().searchMatrix(matrix, 3)
    print Solution().searchMatrix(matrix, 11)
    print Solution().searchMatrix(matrix, 16)
    print Solution().searchMatrix(matrix, 50)

    print Solution().searchMatrix(matrix, 0)
    print Solution().searchMatrix(matrix, 99)
# >>>
# True
# True
# True
# True

# False
# False
