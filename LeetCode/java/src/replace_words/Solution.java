package replace_words;

import java.util.*;

/**
 * Created by Xiaotian on 3/31/18.
 */
class Solution {
    // tag: trie
    // time: O(n*l)
    // space: O(n*l)
    class TrieNode {
        char c;
        boolean isRoot;
        Map<Character, TrieNode> next;
        public TrieNode(char c) {
            this.c = c;
            isRoot = false;
            next = new HashMap<>();
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode(' ');
        }

        public void insert(String s) {
            TrieNode curr = root;
            for (char c : s.toCharArray()) {
                curr.next.putIfAbsent(c, new TrieNode(c));
                curr = curr.next.get(c);
            }
            curr.isRoot = true;
        }

        public String hasRoot(String s) {
            StringBuilder sb = new StringBuilder(); // root
            TrieNode curr = root;
            for (char c : s.toCharArray()) {
                if (!curr.next.containsKey(c)) return "";
                sb.append(c);
                curr = curr.next.get(c);
                if (curr.isRoot) return sb.toString();
            }
            return "";
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String word : dict) {
            trie.insert(word);
        }

        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String root = trie.hasRoot(words[i]);
            if (root.length() != 0) {
                words[i] = root;
            }
            sb.append(words[i] + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
