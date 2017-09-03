package spiral_matrix_ii;

/**
 * Created by Xiaotian on 9/3/17.
 */
public class Solution {
    // tag: array
    // time: O(n*n)
    // space: O(1)
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        if (n == 0) return matrix;

        // boundries
        int rowS = 0;
        int rowE = n - 1;
        int colS = 0;
        int colE = n - 1;

        int num = 1;
        while (rowS <= rowE && colS <= colE) {
            // traverse right
            for (int j = colS; j <= colE; j++) {
                matrix[rowS][j] = num++;
            }
            rowS++;

            // traverse down
            for (int i = rowS; i <= rowE; i++) {
                matrix[i][colE] = num++;
            }
            colE--;

            // traverse left
            if (rowS <= rowE) {
                for (int j = colE; j >= colS; j--) {
                    matrix[rowE][j] = num++;
                }
            }
            rowE--;

            // traverse up
            if (colS <= colE) {
                for (int i = rowE; i >= rowS; i--) {
                    matrix[i][colS] = num++;
                }
            }
            colS++;
        }

        return matrix;
    }
}
