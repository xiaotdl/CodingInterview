package submatrix_sum;

import java.util.*;

/**
 * Created by Xiaotian on 10/5/17.
 */
public class Solution {
    // credit: https://zhengyang2015.gitbooks.io/lintcode/submatrix_sum_405.html
    // 取两个row：l1, l2。用一个线k从左到右扫过l1和l2，每次都用diff=sum[l2][k]-sum[l1][k]来表示l2-l1和0-k这个矩形元素的sum.
    // 如果在同一个l1和l2中，有两条线（k1，k2）的diff相等，则表示l1-l2和k1-k2这个矩形中的元素和为0。
    // tag: dp
    // time: O(n^3)
    // space: O(n^2)
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        int[][] res = new int[2][2];
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;

        int m = matrix.length;
        int n = matrix[0].length;

        // pre-compute: sum[i][j] = sum of submatrix [Matrix(0, 0), Matrix(i-1, j-1)]
        int[][] sum = new int[m+1][n+1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sum[i][j] += matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        // sum rows: i1, i2; sum cols: j
        for (int i1 = 0; i1 < m; i1++) {
            for (int i2 = i1 + 1; i2 < m + 1; i2++) {
                Map<Integer, Integer> map = new HashMap<>(); // diff2ColIndex
                for (int j = 0; j < n + 1; j++) {
                    int diff = sum[i2][j] - sum[i1][j];
                    if (map.containsKey(diff)) {
                        res[0][0] = i1;     res[0][1] = map.get(diff);
                        res[1][0] = i2 - 1; res[1][1] = j - 1;
                        return res;
                    }
                    map.put(diff, j);
                }
            }
        }
        return res;
    }
}
