package the_maze_ii;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // use dists[][] to track min distance to get maze[i][j]
    // tag: dfs
    // time: O(mn)
    // space: O(mn)
    int[][] maze;
    int m;
    int n;
    int minLen;
    public final static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
    public int shortestDistance(int[][] maze, int[] src, int[] dst) {
        this.maze = maze;
        this.m = maze.length;
        this.n = maze[0].length;
        int[][] dists = new int[m][n]; // min distance to get here
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = Integer.MAX_VALUE;
            }
        }
        dists[src[0]][src[1]] = 0;

        dfs(src, dst, dists);

        minLen = dists[dst[0]][dst[1]];
        return minLen != Integer.MAX_VALUE ? minLen : -1;
    }

    private void dfs(int[] src, int[] dst, int[][] dists) {
        if (src[0] == dst[0] && src[1] == dst[1]) return;
        for (int k = 0; k < dirs.length; k++) {
            int row = src[0];
            int col = src[1];
            int dist = dists[row][col];
            while (isValid(row + dirs[k][0], col + dirs[k][1])) {
                dist++;
                row += dirs[k][0];
                col += dirs[k][1];
            }
            if (dists[row][col] > dist) {
                dists[row][col] = dist;
                dfs(new int[]{row, col}, dst, dists);
            }
        }
    }

    private boolean isValid(int r, int c) {
        return 0 <= r && r < m
                && 0 <= c && c < n
                && maze[r][c] != 1;
    }
}
