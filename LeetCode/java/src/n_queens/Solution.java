package n_queens;

import java.util.*;

/**
 * Created by Xiaotian on 2/11/18.
 */
public class Solution {
    // tag: dfs
    // time: O(n^n)
    // space: O(n)
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;

        dfs(n, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int n, List<Integer> cols, List<List<String>> res) {
        if (cols.size() == n) {
            res.add(drawChessboard(cols));
            return;
        }

        for (int col = 0; col < n; col++) { // colIdx
            if (!isValid(cols, col)) continue;

            cols.add(col);
            dfs(n, cols, res);
            cols.remove(cols.size() - 1);
        }
    }

    private boolean isValid(List<Integer> cols, int col) {
        int row = cols.size();
        // by saving points in a col list, we already eliminate multiple points in a same row
        for (int _row = 0; _row < row; _row++) { // rowIdx
            int _col = cols.get(_row);
            if (_col == col) return false;              // same col
            if (_row - _col == row - col) return false; // same top-left to bottom-right diagonal
            if (_row + _col == row + col) return false; // same top-right to bottom-left diagonal
        }
        return true;
    }

    private List<String> drawChessboard(List<Integer> cols) {
        List<String> chessboard = new ArrayList<>();
        int n = cols.size();
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n; j++) {
                row += (cols.get(i) == j ? "Q" : ".");
            }
            chessboard.add(row);
        }
        return chessboard;
    }
}
