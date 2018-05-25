package search_a_2D_matrix_ii;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        return helper(matrix, target, 0, matrix[0].length - 1, 0, matrix.length - 1);
    }

    boolean helper(int[][] matrix, int target, int left, int right, int top , int bottom) {
        // System.out.println(String.format("%s %s %s %s", left, right, top, bottom));
        if (left < 0 || right > matrix[0].length - 1 || top < 0 || bottom > matrix.length - 1
                || left > right || top > bottom) {
            return false;
        }

        int x = top + (bottom - top) / 2;
        int y = left + (right - left) / 2;
        // System.out.println(String.format("    (%s, %s)", x, y));
        if (matrix[x][y] == target) {
            return true;
        }
        else if (matrix[x][y] > target) {
            return helper(matrix, target, left, right, top, x - 1)
                    || helper(matrix, target, left, y - 1, x, bottom);
        }
        else {
            return helper(matrix, target, left, right, x + 1, bottom)
                    || helper(matrix, target, y + 1, right, top, x);
        }
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/search-a-2d-matrix-ii/solution/
    // reduce search space
    // It is not too tricky to see why doing this will never prune the correct answer; because the rows are sorted from left-to-right, we know that every value to the right of the current value is larger. Therefore, if the current value is already larger than target, we know that every value to its right will also be too large. A very similar argument can be made for the columns, so this manner of search will always find target in the matrix (if it is present).

    // tag: matrix
    // time: O(m+n)
    // space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
}
