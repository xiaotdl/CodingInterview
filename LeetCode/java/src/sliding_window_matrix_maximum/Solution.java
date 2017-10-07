package sliding_window_matrix_maximum;

/**
 * Created by Xiaotian on 10/7/17.
 */
public class Solution {
    // sliding window
    // tag: ptr
    // time: O(n)
    // space: O(n)
    /*
     * @param matrix: an integer array of n * m matrix
     * @param k: An integer
     * @return: the maximum number
     */
    public int maxSlidingMatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length < k || matrix[0].length < k) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                preSum[i][j] += matrix[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }

        // enumerate bottom-right point of sliding window matrix
        int max = Integer.MIN_VALUE;
        for (int i = k; i < m + 1; i++) {
            for (int j = k; j < n + 1; j++) {
                max = Math.max(max, preSum[i][j] - preSum[i][j - k] - preSum[i - k][j] + preSum[i - k][j - k]);
            }
        }
        return max;
    }
}
