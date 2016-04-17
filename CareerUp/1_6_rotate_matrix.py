"""
1.6 Given an image represented by an NxN matrix, where each pixel in the image
 is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this
 in place?
"""


def rotate_matrix(matrix):
    # Time complexity: O(n^2) - matrix is iterated once
    # Space complexity: O(n) - created a new matrix of the same size
    res = [[0 for x in range(len(matrix))] for y in range(len(matrix[0]))]
    for i in range(len(matrix[0])):
        for j in range(len(matrix)):
            res[i][j] = matrix[len(matrix) - 1 - j][i]
    return res


def rotate_matrix_in_place(matrix):
    flip_matrix(matrix)
    transpose_matrix(matrix)
    return matrix


def flip_matrix(matrix):
    # Time complexity: O(n^2) - matrix is iterated twice
    # Space complexity: O(1) - in place
    for i in range(len(matrix)):
        for j in range(len(matrix[0])/2):
            tmp = matrix[i][j]
            matrix[i][j] = matrix[i][len(matrix[0]) - 1 - j]
            matrix[i][len(matrix[0]) - 1 - j] = tmp
    return matrix


def transpose_matrix(matrix):
    for i in range(len(matrix)):
        for j in range(0, len(matrix) - 1 - i):
            tmp = matrix[i][j]
            matrix[i][j] = matrix[len(matrix) - 1 - j][len(matrix) - 1 - i]
            matrix[len(matrix) - 1 - j][len(matrix) - 1 - i] = tmp
    return matrix


def main():
    original_matrix = [[1, 2, 3],
                       [4, 5, 6],
                       [7, 8, 9],
                       [10, 11, 12]]
    target_matrix = [[10, 7, 4, 1],
                     [11, 8, 5, 2],
                     [12, 9, 6, 3]]
    print rotate_matrix(original_matrix) == target_matrix
    matrix1 = [[1, 2, 3],
               [4, 5, 6],
               [7, 8, 9]]
    matrix2 = [[7, 4, 1],
               [8, 5, 2],
               [9, 6, 3]]
    print rotate_matrix_in_place(matrix1) == matrix2


if __name__ == "__main__":
    main()
