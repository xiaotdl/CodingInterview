package max_area_of_island;

import java.util.*;

/**
 * Created by Xiaotian on 10/10/17.
 */
public class Solution {
    // tag: bfs
    // time: O(mn)
    // space: O(mn)
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] isVisited = new boolean[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, bfs(grid, isVisited, i, j));
            }
        }
        return max;
    }

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private int bfs(int[][] grid, boolean[][] isVisited, int i, int j) {
        if (grid[i][j] == 0 || isVisited[i][j]) return 0;
        isVisited[i][j] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int k = 0; k < 4; k++) {
                int nextX = curr[0] + dx[k];
                int nextY = curr[1] + dy[k];
                if (inBound(grid.length, grid[0].length, nextX, nextY)
                        && grid[nextX][nextY] == 1
                        && !isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x < m
            && 0 <= y && y < n;
    }
}
