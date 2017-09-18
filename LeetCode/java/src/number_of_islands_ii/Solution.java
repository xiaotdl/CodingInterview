package number_of_islands_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/17/17.
 */
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution {
    // tag: union find
    // time:
    //   find: O(1)
    //   union: O(1)
    // space: O(n)
    class UnionFind {
        int[] parents;

        UnionFind(int n) {
            parents = new int[n + 1];
            for (int x = 1; x <= n; x++) {
                parents[x] = x;
            }
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
            }
        }
    }

    /*
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int m, int n, Point[] operators) {
        List<Integer> res = new ArrayList<Integer>();
        if (operators == null || operators.length == 0) return res;

        int[][] grid = new int[m][n];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        UnionFind uf = new UnionFind(m * n);
        int islandCnt = 0;
        for (int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            if (grid[x][y] == 0) {
                grid[x][y] = 1;
                islandCnt++;
                for (int k = 0; k < 4; k++) {
                    int nextX = x + dx[k];
                    int nextY = y + dy[k];
                    if (inBound(m, n, nextX, nextY) && grid[nextX][nextY] == 1) {
                        int rootCurr = uf.find(x * n + y);
                        int rootNext = uf.find(nextX * n + nextY);
                        if (rootCurr != rootNext) {
                            uf.union(x * n + y, nextX * n + nextY);
                            islandCnt--;
                        }
                    }
                }
            }
            res.add(islandCnt);
        }
        return res;
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }

    public static void main(String[] args) {
        // [[0,0],[0,1],[2,2],[2,2]]
        Point[] operators = new Point[]{new Point(0, 0), new Point(0, 1), new Point(2, 2), new Point(2, 2)};
        System.out.println(new Solution().numIslands2(3, 3, operators));
    }
}
