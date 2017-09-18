package surrounded_regions;

import java.util.*;

/**
 * Created by Xiaotian on 9/18/17.
 */
public class Solution {
    // bfs from all outer coordinates, flip outer 'O' to 'B'
    // tag: bfs
    // time: O(m+n)
    // space: O(m+n)
    class Coordinate {
        int x, y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            bfs(board, 0, j);
            bfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
                else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void bfs(char[][] board, int x, int y) {
        if (board[x][y] != 'O') return;

        board[x][y] = 'B'; // boundary

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Coordinate> q = new LinkedList<>();
        q.offer(new Coordinate(x, y));
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();

            for (int k = 0; k < 4; k++) {
                int nextX = curr.x + dx[k];
                int nextY = curr.y + dy[k];
                if (inBound(board.length, board[0].length, nextX, nextY) && board[nextX][nextY] == 'O') {
                    board[nextX][nextY] = 'B'; // boundary
                    q.offer(new Coordinate(nextX, nextY));
                }
            }
        }
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }
}
