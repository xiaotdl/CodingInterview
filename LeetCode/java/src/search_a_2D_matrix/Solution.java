package search_a_2D_matrix;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0] == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix[0].length;
        int n = matrix.length;
        int start = 0;
        int end = m * n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int x = mid / m;
            int y = mid % m;
            if (matrix[x][y] > target) {
                end = mid;
            }
            else if (matrix[x][y] < target) {
                start = mid;
            }
            else {
                return true;
            }
        }

        if (matrix[start / m][start % m] == target) {
            return true;
        }
        if (matrix[end / m][end % m] == target) {
            return true;
        }

        return false;
    }
}
