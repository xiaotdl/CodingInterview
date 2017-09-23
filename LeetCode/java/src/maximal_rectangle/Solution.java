package maximal_rectangle;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(mn)
    // space: O(mn)
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
                    right[i][j] = Math.min((i - 1 < 0 ? n - 1 : right[i - 1][j]), curr_right);
                }
                else {
                    right[i][j] = n - 1;
                    curr_right = j - 1;
                }
            }
            for (int j = 0; j < n; j++) {
                area[i][j] = (right[i][j] - left[i][j] + 1) * height[i][j];
                max = Math.max(max, area[i][j]);
            }
        }

         System.out.println(Arrays.deepToString(matrix));
         System.out.println(Arrays.deepToString(left));
         System.out.println(Arrays.deepToString(right));
         System.out.println(Arrays.deepToString(height));
         System.out.println(Arrays.deepToString(area));

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximalRectangle(
            new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
            }));
    }

}

class SolutionII {
    // tag: array, stack
    // time: O(mn)
    // space: O(mn)
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] height = new int[m][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    height[i][j] = 0;
                } else {
                    height[i][j] = (i == 0) ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            int area = maxAreaInHistgram(height[i]);
            max = Math.max(max, area);
        }
        return max;
    }

    // Same as LC84. Largest Rectangle in Histogram
    // time: O(n)
    // space: O(n)
    private int maxAreaInHistgram(int[] height) {
        Stack<Integer> stack = new Stack<>();

        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curr = (i == height.length) ? 0 : height[i];
            while (!stack.isEmpty() && curr < height[stack.peek()]) {
                int length = height[stack.pop()];
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                max = Math.max(max, length * width);
            }
            stack.push(i);
        }
        return max;
    }
}
