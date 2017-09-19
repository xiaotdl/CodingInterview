package word_squares;

import java.util.*;

/**
 * Created by Xiaotian on 9/19/17.
 */
public class Solution {
    // tag: backtracking, trie
    // time: O(?)
    // space: O(?)
    class TrieNode {
        List<String> startsWith;
        TrieNode[] next;

        public TrieNode() {
            startsWith = new ArrayList<>();
            next = new TrieNode[26];
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr.next[c - 'a'].startsWith.add(word);
                curr = curr.next[c - 'a'];
            }
        }

        public List<String> searchPrefix(String prefix) {
            List<String> res = new ArrayList<>();
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                if (curr.next[c - 'a'] == null) return res;
                curr = curr.next[c - 'a'];
            }
            res.addAll(curr.startsWith);
            return res;
        }
    }

    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;

        int wordLen = words[0].length();

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> currRes = new ArrayList<>();
        for (String word : words) {
            currRes.add(word);
            search(trie, wordLen, currRes, res);
            currRes.remove(currRes.size() - 1);
        }
        return res;
    }

    private void search(Trie trie, int wordLen, List<String> currRes, List<List<String>> res) {
        if (currRes.size() == wordLen) {
            res.add(new ArrayList<>(currRes));
            return;
        }

        int i = currRes.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String w : currRes) {
            prefixBuilder.append(w.charAt(i));
        }
        List<String> startsWith = trie.searchPrefix(prefixBuilder.toString());
        for (String w : startsWith) {
            currRes.add(w);
            search(trie, wordLen, currRes, res);
            currRes.remove(currRes.size() - 1);
        }

    }
}
