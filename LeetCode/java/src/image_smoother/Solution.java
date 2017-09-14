package image_smoother;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array
    // time: O(mn)
    // space: O(mn)
    public int[][] imageSmoother(int[][] M) {
        int m = M.length;
        int n = M[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = smooth(M, m, n, i, j);
            }
        }
        return res;
    }

    private int smooth(int[][] M, int m, int n, int x, int y) {
        int sum = 0;
        int cnt = 0;
        for (int i = Math.max(0, x - 1); i <= Math.min(m - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(n - 1, y + 1); j++) {
                sum += M[i][j];
                cnt++;
            }
        }
        return sum / cnt;
    }
}
