"""
1.7 Write an algorithm such that if an element in an MxN matrix is 0, its
 entire row and column are set to 0.
"""


def reset_matrix_in_place(matrix):
    # Time complexity: O(mn) - matrix is iterated twice
    # Space complexity: O(1) - in place
    # set the corresponding first row&&column element to 0
    # if m[i][j] is 0
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
    return matrix


def main():
    matrix1 = [[1, 2, 3],
               [4, 5, 0],
               [7, 0, 9],
               [10, 11, 12]]
    matrix2 = [[1, 0, 0],
               [0, 0, 0],
               [0, 0, 0],
               [10, 0, 0]]
    print reset_matrix_in_place(matrix1) == matrix2


if __name__ == "__main__":
    main()
