package word_search;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // tag: array, backtracking, dfs
    // time: O(depth*leafs), depth = word.length, leafs = 4
    // space: O(mn), in place flip board[][] to '*' to improve space to O(1)
    public boolean exist(char[][] board, String word) {
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

class SolutionII {
    // Same as Solution
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, String word, int pos) {
        if (pos == word.length()) return true;
        if (!inBound(board.length, board[0].length, x, y) || board[x][y] != word.charAt(pos)) return false;

        board[x][y] = '*';
        boolean found = dfs(board, x, y + 1, word, pos + 1)
                     || dfs(board, x, y - 1, word, pos + 1)
                     || dfs(board, x + 1, y, word, pos + 1)
                     || dfs(board, x - 1, y, word, pos + 1);
        board[x][y] = word.charAt(pos);
        return found;
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }
}

class SolutionIII {
    // Same as Solution, SolutionII
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, visited, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    public final static int[] dx = {0, 0, -1, 1};
    public final static int[] dy = {-1, 1, 0, 0};
    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int pos) {
        if (pos == word.length()) return true;

        if (!(0 <= x && x < board.length
            && 0 <= y && y < board[0].length)) return false;
        if (visited[x][y]) return false;
        if (board[x][y] != word.charAt(pos)) return false;

        for (int k = 0; k < 4; k++) {
            visited[x][y] = true;
            if (dfs(board, visited, x + dx[k], y + dy[k], word, pos + 1)) return true;
            visited[x][y] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionIII().exist(new char[][]{{'a', 'b'}}, "ba"));
    }
}
