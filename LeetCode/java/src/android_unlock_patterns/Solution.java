package android_unlock_patterns;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dfs
// time: O(n*logn)
// space: O(10^2)
public class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean[] visited = new boolean[10];
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += dfs(visited, skip, 1, i - 1) * 4; // 1, 3, 7, 9 are symmetric
            res += dfs(visited, skip, 2, i - 1) * 4; // 2, 4, 6, 8 are symmetric
            res += dfs(visited, skip, 5, i - 1); // 5
        }
        return res;
    }

    public int dfs(boolean[] visited, int[][] skip, int curr, int left) {
        if (left == 0) return 1;
        visited[curr] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[curr][i] == 0 || visited[skip[curr][i]])) {
                res += dfs(visited, skip, i, left - 1);
            }
        }
        visited[curr] = false;
        return res;
    }
}
