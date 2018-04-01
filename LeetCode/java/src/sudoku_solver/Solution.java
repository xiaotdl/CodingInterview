package sudoku_solver;

/**
 * Created by Xiaotian on 3/17/18.
 */
class Solution {
    // tag: dfs
    // time: O(81^9)
    // space: O(1)
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int i, int j) {
        if (i == 9) return true;

        if (board[i][j] != '.') {
            int[] next = getNext(i, j);
            return dfs(board, next[0], next[1]);
        }

        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, i, j, num)) {
                board[i][j] = num;
                int[] next = getNext(i, j);
                if (dfs(board, next[0], next[1])) return true;
                board[i][j] = '.';
            }
        }
        return false;
    }

    private int[] getNext(int i, int j) {
        int nextI = (j == 8 ? i + 1 : i);
        int nextJ = (j == 8 ? 0 : j + 1);
        return new int[]{nextI, nextJ};
    }

    private boolean isValid(char[][] board, int i, int j, char c) {
        for(int k = 0; k < 9; k++) {
            if(board[k][j] != '.' && board[k][j] == c) return false;
            if(board[i][k] != '.' && board[i][k] == c) return false;
            if(board[3*(i/3) + k/3][3*(j/3) + k%3] != '.' && board[3*(i/3) + k/3][3*(j/3) + k%3] == c) return false;
        }
        return true;
    }
}
