import java.util.ArrayList;

public class NQueens {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */

    // V1, O(n!)?
    // Permutation, Recursion
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (n <= 0) {
            return result;
        }
        search(n, new ArrayList<Integer>(), result);
        return result;
    }
    private void search(int n, ArrayList<Integer> cols, ArrayList<ArrayList<String>> result) {
        if (cols.size() == n) {
            result.add(drawChessboard(cols));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(cols, col)) {
                continue;
            }
            cols.add(col);
            search(n, cols, result);
            cols.remove(cols.size() - 1);
        }
    }
    private boolean isValid(ArrayList<Integer> cols, int col) {
        int row = cols.size();
        for (int i = 0; i < row; i++) {
            // col check
            if (cols.get(i) == col) {
                return false;
            }
            // top-left to bottom-right check
            if (i - cols.get(i) == row - col) {
                return false;
            }
            // top-right to bottom-left check
            if (i + cols.get(i) == row + col) {
                return false;
            }
        }
        return true;
    }
    private ArrayList<String> drawChessboard(ArrayList<Integer> cols) {
        ArrayList<String> chessboard = new ArrayList<String>(cols.size());
        for (int i = 0; i < cols.size(); i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < cols.size(); j++) {
                if (j == cols.get(i)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }
};

