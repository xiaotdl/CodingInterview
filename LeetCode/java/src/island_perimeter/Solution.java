package island_perimeter;

/**
 * Created by Xiaotian on 2/10/18.
 */
public class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    if (isSide(i + dx[k], j + dy[k], m, n, grid)) {
                        perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }

    private boolean isSide(int x, int y, int m, int n, int[][] grid) {
        if (0 <= x && x < m
            && 0 <= y && y < n) {
            return grid[x][y] == 0;
        }
        return true;
    }
}
