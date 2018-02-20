package the_maze;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // use visited[][] to track maze[i][j] visited
    // tag: dfs
    // time: O(mn)
    // space: O(mn)
    int[][] maze;
    int m;
    int n;
    public static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
    public boolean hasPath(int[][] maze, int[] src, int[] dst) {
        if (maze == null || maze.length == 0) return false;

        this.maze = maze;
        this.m = maze.length;
        this.n = maze[0].length;
        boolean[][][] visited = new boolean[m][n][4]; // up, down, left, right
        for (int d = 0; d < dirs.length; d++) {
            visited[src[0]][src[1]][d] = true;
            if (dfs(src, dst, d, visited, "")) return true;
        }
        return false;
    }

    private boolean dfs(int[] src, int[] dst, int d, boolean[][][] visited, String lvl) {
        System.out.println(lvl + "x: " + src[0] + " y: " + src[1] + " dir: " + d);
        int[] next = new int[]{
                src[0] + dirs[d][0],
                src[1] + dirs[d][1]};
        if (inBound(next) && maze[next[0]][next[1]] != 1) {
            System.out.println(lvl + "keep going " + d);
            if (dfs(next, dst, d, visited, lvl+"  ")) return true;
        }
        else {
            System.out.println(lvl + "hit wall");
            if (src[0] == dst[0] && src[1] == dst[1]) return true;

            for (int _d = 0; _d < dirs.length; _d++) {
                if (visited[src[0]][src[1]][_d]) return false;
                visited[src[0]][src[1]][_d] = true;
                if (dfs(src, dst, _d, visited, lvl+"  ")) return true;
            }
        }
        return false;
    }

    private boolean inBound(int[] next) {
        return 0 <= next[0] && next[0] < m
                && 0 <= next[1] && next[1] < n;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };
        int[] src = new int[]{0, 4};
        int[] dst = new int[]{1, 2};
        System.out.println(new Solution().hasPath(maze, src, dst));
    }
}

class SolutionII {
    // Same as Solution
    int[][] maze;
    int m;
    int n;
    public static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
    public boolean hasPath(int[][] maze, int[] src, int[] dst) {
        this.maze = maze;
        this.m = maze.length;
        this.n = maze[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(src, dst, visited);
    }

    private boolean dfs(int[] src, int[] dst, boolean[][] visited) {
        if (visited[src[0]][src[1]]) return false;
        if (src[0] == dst[0] && src[1] == dst[1]) return true;
        visited[src[0]][src[1]] = true;
        for (int k = 0; k < dirs.length; k++) {
            int[] d = dirs[k];
            int row = src[0];
            int col = src[1];
            while (isValid(row + d[0], col + d[1])) {
                row += d[0];
                col += d[1];
            }
            if (dfs(new int[]{row, col}, dst, visited)) return true;
        }
        return false;
    }

    private boolean isValid(int row, int col) {
        return 0 <= row && row < m
                && 0 <= col && col < n
                && maze[row][col] != 1;
    }
}
