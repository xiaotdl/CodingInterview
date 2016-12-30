package maximal_rectangle;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n^2)
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0; // max area

        // height[i][j]: counts the number of successive '1's above (including Point(i, j)).
        // left/right[i][j]: records the index of the boundaries of the rectangle (including Point(i, j)).
        int[][] height = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int[][] area = new int[m][n]; // debug

        for (int i = 0; i < m; i++) {
            int curr_left = 0;
            int curr_right = n;
            for (int j = 0; j < n; j++) {
                height[i][j] = matrix[i][j] == '1' ? (i - 1 < 0 ? 0 : height[i - 1][j]) + 1 : 0;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = Math.max((i - 1 < 0 ? 0 : left[i - 1][j]), curr_left);
                }
                else {
                    left[i][j] = 0;
                    curr_left = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[i][j] = Math.min((i - 1 < 0 ? n : right[i - 1][j]), curr_right);
                }
                else {
                    right[i][j] = n;
                    curr_right = j;
                }
            }
            for (int j = 0; j < n; j++) {
                area[i][j] = (right[i][j] - left[i][j]) * height[i][j];
                max = Math.max(max, area[i][j]);
            }
        }

        // ["10100","10111","11111","10010"]
        // System.out.println(Arrays.deepToString(left));
        // System.out.println(Arrays.deepToString(right));
        // System.out.println(Arrays.deepToString(height));
        // System.out.println(Arrays.deepToString(area));

        return max;
    }
}
