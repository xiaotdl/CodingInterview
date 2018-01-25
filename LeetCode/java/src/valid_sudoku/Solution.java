package valid_sudoku;

import java.util.*;

/**
 * Created by Xiaotian on 1/25/18.
 */
public class Solution {
    // 行、列、块三种验证
    // Hash （标记数组）练习题
    // 如何算出这个位置属于第几块？
    // tag: array
    // time: O(mn)
    // space: O(1)
    /*
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];

        // validate row
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!validate(visited, board[i][j])) {
                    return false;
                }
            }
        }

        // validate column
        for (int j = 0; j < 9; j++) {
            Arrays.fill(visited, false);
            for (int i = 0; i < 9; i++) {
                if (!validate(visited, board[i][j])) {
                    return false;
                }
            }
        }

        // validate block/submatrix
        for(int i = 0; i < 9; i += 3){
            for(int j = 0; j < 9; j += 3){
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++){
                    if (!validate(visited, board[i + k/3][j + k%3])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean validate(boolean[] visited, char c) {
        if (c == '.') {
            return true;
        }

        int num = c - '0';
        if (1 <= num && num <= 9 && !visited[num - 1]) {
            visited[num - 1] = true;
            return true;
        }

        return false;
    }
}
