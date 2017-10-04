package k_edit_distance;

import java.util.*;

/**
 * Created by Xiaotian on 10/3/17.
 */
public class Solution {
    // dp指的是上一次的情况。你就把它看成是我们上课讲的那个edit矩阵，我现在更新i，j这个格子，那么我要往左上方看(i-1, j-1)，要往上看(i-1, j)，这两种就是分别对应dp数组里的dp[j-1]和dp[j]。然后我还要往左边看i, j-1，此时行不变，相当于我看next数组里我左边那个刚刚更新过的值，所以是next[j-1]
    // tag: dfs, dp, 滚动数组
    // time: O(l*m*n), l: num of words, m: avg word length, n: length of target
    // space: O(n)
    class TrieNode {
        public String word;
        public boolean hasWord;
        public TrieNode[] next;

        public TrieNode() {
            hasWord = false;
            next = new TrieNode[26];
        }
    }

    class Trie {
        private TrieNode root;

        public Trie () {
            this.root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
            curr.hasWord = true;
        }
    }

    /*
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.addWord(word);
        }

        int n = target.length();
        // dp[i]: edit distance of TrieString[0..dfs_level] and target[0..j]
        // dp[i]表示从Trie的root节点走到当前node节点形成的Prefix
        //      和target的前j个字符的最小编辑距离
        int[] dp = new int[n + 1];
        for (int j = 0; j < dp.length; j++) {
            dp[j] = j;
        }

        dfs(trie.root, target, k, dp, res);
        return res;
    }

    private void dfs(TrieNode node, String target, int k, int[] currRow, List<String> res) {
        if (node == null) return;

        int n = target.length();
        if (node.hasWord && currRow[n] <= k) {
            res.add(node.word);
        }

        int[] nextRow = new int[n + 1];
        for (int i = 0; i < 26; i++) {
            if (node.next[i] == null) continue;

            nextRow[0] = currRow[0] + 1;
            for (int j = 1; j <= n; j++) {
                if (target.charAt(j - 1) - 'a' == i) {
                    nextRow[j] = Collections.min(Arrays.asList(nextRow[j - 1] + 1, currRow[j - 1],  currRow[j] + 1));
                } else {
                    nextRow[j] = Collections.min(Arrays.asList(nextRow[j - 1] + 1, currRow[j - 1] + 1, currRow[j] + 1));
                }
            }
            dfs(node.next[i], target, k, nextRow, res);
        }
    }
}
