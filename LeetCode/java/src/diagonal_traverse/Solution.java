package diagonal_traverse;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/diagonal-traverse/discuss/97712/Concise-Java-Solution
    // tag: matrix
    // time: O(mn)
    // space: O(1)
    private final static int[] dx = {-1, 1};
    private final static int[] dy = {1, -1};
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length;
        int n = matrix[0].length;

        int[] res = new int[m*n];
        int i = 0;
        int j = 0;
        int dir = 0; // 0: towards top-right, 1: towards bottom-left
        for (int k = 0; k < m*n; k++) {
            res[k] = matrix[i][j];
            i += dx[dir];
            j += dy[dir];
            if (i >= m) { i = m - 1; j += 2; dir = 1 - dir; }
            if (j >= n) { j = n - 1; i += 2; dir = 1 - dir; }
            if (i < 0) { i = 0; dir = 1 - dir; }
            if (j < 0) { j = 0; dir = 1 - dir; }
        }
        return res;
    }
}
