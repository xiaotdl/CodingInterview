package max_sum_of_rectangle_no_larger_than_k;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
// time: O(n^4)
// space: O(n^2)
public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        // sum[i][j]: sumSubmatrix of rectangle (0, 0) -> (i, j)
        int[][] sum = new int[m][n];
        sum[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            sum[i][0] = sum[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < n; j++) {
            sum[0][j] = sum[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i][j];
            }
        }

        int max = Integer.MIN_VALUE;;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int h = 0; h <= m - 1 - i; h++) {
                    for (int w = 0; w <= n - 1 - j; w++) {
                        // Rectangle:
                        // (i, j)     (i, j+w)
                        // (i+h, j)   (i+h, j+w)
                        int rectSum = 0;
                        if (i == 0 && j == 0) {
                            rectSum = sum[i+h][j+w];
                        }
                        else if (i == 0) {
                            rectSum = sum[i+h][j+w] - sum[i+h][j-1];
                        }
                        else if (j == 0) {
                            rectSum = sum[i+h][j+w] - sum[i-1][j+w];
                        }
                        else {
                            rectSum = sum[i+h][j+w] - sum[i+h][j-1] - sum[i-1][j+w] + sum[i-1][j-1];
                        }

                        if (rectSum <= k) {
                            max = Math.max(max, rectSum);
                        }
                    }
                }
            }
        }
        return max;
    }
}
