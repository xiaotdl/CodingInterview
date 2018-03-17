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

class SolutionII {
    // use number of visited matrix node to terminate traversal
    // tag: array
    // time: O(m*n)
    // space: O(1)
    /*
     * @param matrix: a matrix of m x n elements
     * @return: an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;

        int m = matrix.length;
        int n = matrix[0].length;
        int rowS = 0, rowE = m - 1;
        int colS = 0, colE = n - 1;

        while (res.size() != m*n) {
            // go right
            for (int j = colS; j <= colE; j++) {
                res.add(matrix[rowS][j]);
            }
            rowS++;
            if (res.size() == m*n) break;

            // go down
            for (int i = rowS; i <= rowE; i++) {
                res.add(matrix[i][colE]);
            }
            colE--;
            if (res.size() == m*n) break;

            // go left
            for (int j = colE; j >= colS; j--) {
                res.add(matrix[rowE][j]);
            }
            rowE--;
            if (res.size() == m*n) break;

            // go up
            for (int i = rowE; i >= rowS; i--) {
                res.add(matrix[i][colS]);
            }
            colS++;
        }
        return res;
    }
}
