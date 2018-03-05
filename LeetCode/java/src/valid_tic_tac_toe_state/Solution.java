package valid_tic_tac_toe_state;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: matrix
    // time: O(n^2)
    // space: O(1)
    public boolean validTicTacToe(String[] board) {
        int m = board.length;
        int n = board[0].length();
        int xCnt = 0;
        int oCnt = 0;
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                g[i][j] = c;
                if (c == 'X') xCnt++;
                if (c == 'O') oCnt++;
            }
        }
        if (!(xCnt == oCnt || xCnt == oCnt + 1)) return false;

        int xWinCnt = calculateWinCnt(g, 'X');
        int oWinCnt = calculateWinCnt(g, 'O');

        if (xWinCnt > 1 || oWinCnt > 1) return false;
        if (xWinCnt == 1) return xCnt == oCnt + 1;
        if (oWinCnt == 1) return xCnt == oCnt;
        return true;
    }

    private int calculateWinCnt(char[][] g, char c) {
        int m = g.length;
        int n = g[0].length;
        int winCnt = 0;
        // row
        for (int i = 0; i < m; i++) {
            boolean rowSame = true;
            for (int j = 0; j < n; j++) {
                if (g[i][j] != c) rowSame = false;
            }
            if (rowSame) winCnt++;
        }
        // col
        for (int j = 0; j < n; j++) {
            boolean colSame = true;
            for (int i = 0; i < m; i++) {
                if (g[i][j] != c) colSame = false;
            }
            if (colSame) winCnt++;
        }
        // top-left to bottom-right diag
        boolean diagSame1 = true;
        for (int i = 0; i < m; i++) {
            int j = i;
            if (g[i][j] != c) diagSame1 = false;
        }
        if (diagSame1) winCnt++;
        // top-right to bottom-left diag
        boolean diagSame2 = true;
        for (int i = m - 1; i >= 0; i--) {
            int j = n - i - 1;
            if (g[i][j] != c) diagSame2 = false;
        }
        return winCnt;
    }
}
