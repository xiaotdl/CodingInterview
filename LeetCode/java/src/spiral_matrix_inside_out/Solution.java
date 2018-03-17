package spiral_matrix_inside_out;

import java.util.Stack;

/**
 * Created by Xiaotian on 3/15/18.
 */
public class Solution {
    public void printMatrixInSpiralOrder(int[][] matrix) {
        Stack<Integer> stack = new Stack<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;
        int rowS = 0, rowE = m - 1;
        int colS = 0, colE = n - 1;

        while (stack.size() != m*n) {
            // go right
            for (int j = colS; j <= colE; j++) {
                stack.add(matrix[rowS][j]);
            }
            rowS++;

            // go down
            for (int i = rowS; i <= rowE; i++) {
                stack.add(matrix[i][colE]);
            }
            colE--;

            // go left
            for (int j = colE; j >= colS; j--) {
                stack.add(matrix[rowE][j]);
            }
            rowE--;

            // go up
            for (int i = rowE; i >= rowS; i--) {
                stack.add(matrix[i][colS]);
            }
            colS++;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//            {1,2,3,4,5},
//            {16,17,18,19,6},
//            {15,24,25,20,7},
//            {14,23,22,21,8},
//            {13,12,11,10,9}
//        };
        int[][] matrix = new int[][]{
            {1,2,3,4},
            {12,13,14,5},
            {11,16,15,6},
            {10,9,8,7},
        };
        new Solution().printMatrixInSpiralOrder(matrix);
    }
}
