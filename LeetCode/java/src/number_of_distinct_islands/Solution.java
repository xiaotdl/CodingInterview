package number_of_distinct_islands;

import java.util.*;

/**
 * Created by Xiaotian on 10/10/17.
 */
public class Solution {
    // tag: dfs
    // time: O(mn)
    // space: O(mn)
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] isVisited = new boolean[m][n];
        Set<List<List<Integer>>> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<List<Integer>> island = new ArrayList<>();
                if (dfs(grid, isVisited, i, j, i, j, island)) {
                    islands.add(island);
                }
            }
        }
        return islands.size();
    }

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private boolean dfs(int[][] grid, boolean[][] isVisited,
                        int i0, int j0, int i, int j, List<List<Integer>> island) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        if (grid[i][j] == 0 || isVisited[i][j]) return false;
        isVisited[i][j] = true;

        island.add(Arrays.asList(i - i0, j - j0));
        for (int k = 0; k < dx.length; k++) {
            dfs(grid, isVisited, i0, j0, i + dx[k], j + dy[k], island);
        }
        return true;
    }
}
