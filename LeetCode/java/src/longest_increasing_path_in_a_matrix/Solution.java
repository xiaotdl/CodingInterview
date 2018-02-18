package longest_increasing_path_in_a_matrix;

/**
 * Created by Xiaotian on 2/17/18.
 */
public class Solution {
    // tag: dp
    // time: O(mn), each cell is visited only once
    // space: O(mn)
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, cache));
            }
        }
        return maxLen;
    }

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];

        int maxLen = 1;
        for (int k = 0; k < 4; k++) {
            int nextX = i + dx[k];
            int nextY = j + dy[k];
            if (0 <= nextX && nextX < m
                    && 0 <= nextY && nextY < n
                    && matrix[nextX][nextY] > matrix[i][j]) {
                maxLen = Math.max(maxLen, dfs(matrix, nextX, nextY, cache) + 1);
            }
        }
        cache[i][j] =  maxLen;
        return cache[i][j];
    }
}
