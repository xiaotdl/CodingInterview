package the_maze_iii;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // Ref: http://www.cnblogs.com/grandyang/p/6746528.html
    // tag: dfs
    // time: O(mn)
    // space: O(mn)
    int[][] maze;
    int m;
    int n;
    int minLen;
    public final static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public final static String[] ways = new String[]{"u", "d", "l", "r"};
    public String findShortestWay(int[][] maze, int[] src, int[] dst) {
        this.maze = maze;
        this.m = maze.length;
        this.n = maze[0].length;
        int[][] dists = new int[m][n]; // min distance to get maze[i][j]
        String[][] paths = new String[m][n]; // min path to get maze[i][j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = Integer.MAX_VALUE;
                paths[i][j] = "";
            }
        }
        dists[src[0]][src[1]] = 0;

        dfs(src, dst, dists, paths);

        String minPath = paths[dst[0]][dst[1]];
        return minPath.length() != 0 ? minPath : "impossible";
    }

    private void dfs(int[] src, int[] dst, int[][] dists, String[][] paths) {
        if (src[0] == dst[0] && src[1] == dst[1]) return;

        for (int k = 0; k < dirs.length; k++) {
            int row = src[0];
            int col = src[1];
            int dist = dists[row][col];
            String path = paths[row][col] + ways[k];
            while (isValid(row + dirs[k][0], col + dirs[k][1], dst)) {
                dist++;
                row += dirs[k][0];
                col += dirs[k][1];
                if (row == dst[0] && col == dst[1]) break;
            }
            if (dists[row][col] > dist) {
                dists[row][col] = dist;
                paths[row][col] = path;
                dfs(new int[]{row, col}, dst, dists, paths);
            }
            else if (dists[row][col] == dist && paths[row][col].compareTo(path) > 0) {
                paths[row][col] = path;
                dfs(new int[]{row, col}, dst, dists, paths);
            }
        }
    }

    private boolean isValid(int r, int c, int[] dst) {
        return 0 <= r && r < m
                && 0 <= c && c < n
                && maze[r][c] == 0;
    }
}
