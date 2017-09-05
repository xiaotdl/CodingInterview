package word_search_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // TLE
    // tag: array, backtracking
    // time: O(depth*leafs), depth = word.length, leafs = 4
    // space: O(mn)
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (!res.contains(word) && findWord(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean findWord(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;

        char[] W = word.toCharArray();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, visited, i, j, W, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, boolean[][] visited, int i, int j, char[] word, int len) {
        if (len == word.length) return true;
        if (i < 0 || j < 0 || i == board.length || j == board[0].length) return false;
        if (visited[i][j]) return false;
        if (board[i][j] != word[len]) return false;
        visited[i][j] = true;
        if (exist(board, visited, i, j - 1, word, len + 1)
                || exist(board, visited, i, j + 1, word, len + 1)
                || exist(board, visited, i - 1, j, word, len + 1)
                || exist(board, visited, i + 1, j, word, len + 1)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}
