package bomb_enemy;

/**
 * Created by Xiaotian on 12/26/16.
 */
// tag: dp
// time: O(mn)
// space: O(m+n), can be optimized to n using int rowKills
public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int maxKills = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] rowKills = new int[m];
        int[] colKills = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colKills[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colKills[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowKills[i] = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowKills[i] += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') {
                    maxKills = Math.max(maxKills, rowKills[i] + colKills[j]);
                }
            }
        }
        return maxKills;
    }
}