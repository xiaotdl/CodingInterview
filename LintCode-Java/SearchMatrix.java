public class SearchMatrix {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */

    // V1, O(logm + logn)
    // Iteration, Binary Search
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if(matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int start, end, mid;
        int row, col;

        start = 0;
        end = matrix.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }

        start = 0;
        end = matrix[0].length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                end = mid;
            } else if (matrix[row][mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (matrix[row][start] == target) {
            return true;
        } else if (matrix[row][end] == target) {
            return true;
        } else {
            return false;
        }
    }

    // V2, O(m + n), just for fun
    // Iteration
    public static boolean searchMatrix_2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if(matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row, col, i, j;
        row = matrix.length;
        col = matrix[0].length;
        i = row - 1;
        j = 0;

        while (i >= 0 && j < col) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        return false;
    }

    // V3, same as V1, O(logm*n)
    // Iteration, one-time Binary Search
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int start = 0;
        int end = row * column - 1;
        int mid;
        int number;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            number = matrix[mid / column][mid % column];
            if (number == target) {
                return true;
            }
            else if (number < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (matrix[start / column][start % column] == target
                || matrix[end / column][end % column] == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(searchMatrix(matrix, 8));
        System.out.println(searchMatrix(matrix, 3));
        System.out.println(searchMatrix(matrix, 99));
        System.out.println(searchMatrix_2(matrix, 0));
        System.out.println(searchMatrix_2(matrix, 8));
        System.out.println(searchMatrix_2(matrix, 3));
        System.out.println(searchMatrix_2(matrix, 99));
        System.out.println(searchMatrix_2(matrix, 0));
    }
}
