package number_of_islands;

import java.util.*;

/**
 * Created by Xiaotian on 9/17/17.
 */
public class Solution {
    // tag: dfs
    // time: O(mn)
    // space: O(1)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                res++;
                dfs(grid, i, j); // mark adjacent islands visited
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1) return;

        if (grid[i][j] == '0') return;

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}

class SolutionII {
    // tag: bfs
    // time: O(mn)
    // space: O(1)
    class Coordinate {
        int x, y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                res++;
                bfs(grid, i, j); // mark adjacent islands visited
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1) return;

        if (grid[x][y] == '0') return;

        // directions
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Coordinate> q = new LinkedList<>();
        q.offer(new Coordinate(x, y));
        grid[x][y] = '0';
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = curr.x + dx[i];
                int nextY = curr.y + dy[i];
                Coordinate adjacent = new Coordinate(nextX, nextY);
                if (inBound(m, n, adjacent.x, adjacent.y) && grid[nextX][nextY] == '1') {
                    q.offer(adjacent);
                    grid[nextX][nextY] = '0';
                }
            }
        }
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }
}

class SolutionIII {
    // tag: union find
    // time: O(mn)
    // space: O(mn)
    class UnionFind {
        int[] parents;
        int count;

        UnionFind(int n, int count) {
            parents = new int[n + 1];
            for (int x = 1; x <= n; x++) {
                parents[x] = x;
            }
            this.count = count;
        }

        public int query() {
            return count;
        }

        public void connect(int a, int b) {
            union(a, b);
        }

        private int find(int x) {
            if (parents[x] == x) return x;
            parents[x] = find(parents[x]);
            return parents[x];
        }

        private void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parents[rootA] = rootB;
                count--;
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int islandsCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') islandsCnt++;
            }
        }

        UnionFind uf = new UnionFind(m * n, islandsCnt);

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;

                for (int k = 0; k < 4; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];
                    if (inBound(m, n, nextX, nextY) && grid[nextX][nextY] == '1') {
                        uf.connect(i * n + j, nextX * n + nextY);
                    }
                }
            }
        }
        return uf.query();
    }

    private boolean inBound(int m, int n, int x, int y) {
    return 0 <= x && x <= m - 1
        && 0 <= y && y <= n - 1;
    }
}
