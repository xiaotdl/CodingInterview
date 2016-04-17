import java.util.ArrayList;

public class NQueensII {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */

    // V1, O(n!)?
    // Permutation, Recursion
    public int totalNQueens(int n) {
        result = 0;
        if (n <= 0) {
            return result;
        }
        search(n, new ArrayList<Integer>());
        return result;
    }
    public static int result;
    private void search(int n, ArrayList<Integer> cols) {
        if (cols.size() == n) {
            result++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(cols, col)) {
                continue;
            }
            cols.add(col);
            search(n, cols);
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
};


