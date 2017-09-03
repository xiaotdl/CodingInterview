package rotate_image;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // flip diagnally, 135 degree
        // swap (i, j) with (j, i)
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // flip vertically
        // swap (i, j) with (i, n - 1 - j)
        for(int i =0 ; i < n; i++){
            for(int j = 0; j < n / 2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }
}
