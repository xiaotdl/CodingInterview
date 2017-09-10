package game_of_life;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // tag: array
    // time: O(mn)
    // space: O(mn)
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] next = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = getNumOfLiveNeighbors(board, i, j);
                if (board[i][j] == 1) {
                    if (num < 2) next[i][j] = 0;
                    else if (num == 2 || num == 3) next[i][j] = 1;
                    else next[i][j] = 0;
                }
                else {
                    if (num == 3) next[i][j] = 1;
                    else next[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    // (x-1, y-1) (x-1, y) (x-1, y+1)
    // (x  , y-1) (x  , y) (x  , y+1)
    // (x+1, y-1) (x+1, y) (x+1, y+1)
    private int getNumOfLiveNeighbors(int[][] board, int x, int y) {
        int num = 0;
        int m = board.length;
        int n = board[0].length;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, m - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, n - 1); j++) {
                if (i == x && j == y) continue;
                num += board[i][j];
            }
        }
        return num;
    }
}
