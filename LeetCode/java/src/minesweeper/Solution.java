package minesweeper;

import java.util.*;

/**
 * Created by Xiaotian on 4/23/18.
 */
class Solution {
    // 'M' represents an unrevealed mine
    // 'E' represents an unrevealed empty square

    // 'X' represents a revealed mine
    // 'B' represents a revealed blank square that has no adjacent mines('M' || 'X')

    // digit ('1' to '8') represents how many mines are adjacent to this revealed square

    // tag: bfs
    // time: O(mn)
    // space: O(mn)
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0];
            int y = cell[1];

            // upon Mine
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                continue;
            }

            // upon Empty
            int cnt = 0; // bomb cnt
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (!(0 <= i && i < m && 0 <= j && j < n)) continue;
                    if (i == x && j == y) continue;
                    if (board[i][j] == 'M' || board[i][j] == 'X') cnt++;
                }
            }

            if (cnt > 0) { // change into Digit, stop further BFS.
                board[x][y] = (char)(cnt + '0');
                continue;
            }

            // => change into Blank, continue BFS to adjacent cells.
            board[x][y] = 'B';
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (!(0 <= i && i < m && 0 <= j && j < n)) continue;
                    if (i == x && j == y) continue;
                    if (board[i][j] == 'E') {
                        q.offer(new int[]{i, j});
                        board[i][j] = 'B';
                    }
                }
            }
        }
        return board;
    }
}
