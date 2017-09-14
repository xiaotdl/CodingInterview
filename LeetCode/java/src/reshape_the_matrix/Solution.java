package reshape_the_matrix;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array
    // time: O(mn)
    // space: O(mn)
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null) return null;

        int m = nums.length;
        int n = nums[0].length;
        if (m*n != r*c) return nums;

        int[][] res = new int[r][c];
        for (int i = 0; i < r*c; i++) {
            res[i/c][i%c] = nums[i/n][i%n];
        }
        return res;
    }
}
