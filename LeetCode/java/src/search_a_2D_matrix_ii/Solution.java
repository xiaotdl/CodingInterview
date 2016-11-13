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
