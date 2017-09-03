package spiral_matrix;

import java.util.*;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: array
    // time: O(m*n)
    // space: O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int m = matrix.length;
        int n = matrix[0].length;
        // boundries
        int rowS = 0;
        int rowE = m - 1;
        int colS = 0;
        int colE = n - 1;

        while (rowS <= rowE && colS <= colE) {
            // traverse right
            for (int j = colS; j <= colE; j++) {
                res.add(matrix[rowS][j]);
            }
            rowS++;

            // traverse down
            for (int i = rowS; i <= rowE; i++) {
                res.add(matrix[i][colE]);
            }
            colE--;

            // traverse left
            if (rowS <= rowE) {
                for (int j = colE; j >= colS; j--) {
                    res.add(matrix[rowE][j]);
                }
            }
            rowE--;

            // traverse up
            if (colS <= colE) {
                for (int i = rowE; i >= rowS; i--) {
                    res.add(matrix[i][colS]);
                }
            }
            colS++;
        }

        return res;
    }
}
