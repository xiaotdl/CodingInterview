package walls_and_gates;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // BFS from gate to empty spaces
    // tag: bfs
    // time: O(mn)
    // space: O(mn)
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == GATE) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if (0 <= nextX && nextX < m && 0 <= nextY && nextY < n && rooms[nextX][nextY] == EMPTY) {
                    rooms[nextX][nextY] = rooms[x][y] + 1;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}
