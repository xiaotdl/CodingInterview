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

class SolutionII {
    // save word in trie node
    // tag: array, dfs, trie
    // time: O(depth*leafs), depth = word.length, leafs = 4
    // space: O(mn)
    class TrieNode {
        String word;
        Map<Character, TrieNode> next;
        boolean hasWord;
        public TrieNode() {
            word = "";
            next = new HashMap<>();
            hasWord = false;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!curr.next.containsKey(c)) {
                    curr.next.put(c, new TrieNode());
                }
                curr = curr.next.get(c);
            }
            curr.word = s;
            curr.hasWord = true;
        }

        public boolean search(String s) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!curr.next.containsKey(c)) {
                    return false;
                }
                curr = curr.next.get(c);
            }
            return curr != null && curr.hasWord;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) return res;

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, trie.root, res);
            }
        }
        return res;
    }

    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    private void dfs(char[][] board, int x, int y, TrieNode node, List<String> res) {
        if (node.hasWord == true && !res.contains(node.word)) {
            res.add(node.word);
        }

        if (!inBound(board.length, board[0].length, x, y) || board[x][y] == '*') return;

        if (node.next.containsKey(board[x][y])) {
            for (int k = 0; k < 4; k++) {
                char origChar = board[x][y];
                board[x][y] = '*'; // mark as visited
                dfs(board, x + dx[k], y + dy[k], node.next.get(origChar), res);
                board[x][y] = origChar;
            }
        }
    }

    private boolean inBound(int m, int n, int x, int y) {
        return 0 <= x && x <= m - 1
            && 0 <= y && y <= n - 1;
    }
}
