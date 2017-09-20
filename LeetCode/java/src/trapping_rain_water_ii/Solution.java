package trapping_rain_water_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/19/17.
 */
public class Solution {
    // tag: bfs, heap
    // time: O(mn)
    // space: O(mn)
    class Coordinate {
        int x, y, height;

        public Coordinate(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    /*
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) return 0;

        int m = heights.length;
        int n = heights[0].length;

        // minHeap
        PriorityQueue<Coordinate> q = new PriorityQueue<>(2 * m + 2 * n,
                new Comparator<Coordinate>() {
                    @Override
                    public int compare(Coordinate a, Coordinate b) {
                        return a.height - b.height;
                    }
                }
        );

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            q.offer(new Coordinate(i, 0, heights[i][0]));
            q.offer(new Coordinate(i, n - 1, heights[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            q.offer(new Coordinate(0, j, heights[0][j]));
            q.offer(new Coordinate(m - 1, j, heights[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int res = 0;
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();

            for (int k = 0; k < 4; k++) {
                int nextX = curr.x + dx[k];
                int nextY = curr.y + dy[k];
                if (inBound(m, n, nextX, nextY) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.offer(new Coordinate(
                            nextX, nextY,
                            Math.max(curr.height, heights[nextX][nextY])));
                    res += Math.max(0, curr.height - heights[nextX][nextY]);
                }
            }
        }
        return res;
    }

    private boolean inBound(int m , int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }
}
