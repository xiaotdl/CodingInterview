package nearest_exit;

import java.util.*;

/**
 * Created by Xiaotian on 2/2/18.
 */
public class Solution {
    // tag: bfs
    // time: O(mn)
    // space: O(mn)
    /*
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;

        int INF = 2147483647;
        int m = rooms.length;
        int n = rooms[0].length;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }

        while (!qx.isEmpty() && !qy.isEmpty()) {
            int currX = qx.poll();
            int currY = qy.poll();

            for (int k = 0; k <= 3; k++) {
                int nextX = currX + dx[k];
                int nextY = currY + dy[k];
                if (inBound(m, n, nextX, nextY) && rooms[nextX][nextY] == INF) {
                    qx.offer(nextX);
                    qy.offer(nextY);
                    rooms[nextX][nextY] = rooms[currX][currY] + 1;
                }
            }
        }
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x < m
            && 0 <= y && y < n;
    }
}
