package implement_trie;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
}

// credit: https://leetcode.com/articles/implement-trie-prefix-tree/
class Trie {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    // time: O(m), m: word length
    // space: O(m), worst case: needs to create m TrieNodes when there is no such prefix
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                return null;
            }
            node = node.get(c);
        }
        return node;
    }

    // time: O(m)
    // space: O(1)
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // time: O(m)
    // space: O(1)
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

class TrieNode {
    private TrieNode[] next;
    private final int R = 26;
    private boolean isEnd;

    public TrieNode() {
        next = new TrieNode[R];
    }

    public boolean containsKey(char c) {
        return next[c - 'a'] != null;
    }

    public TrieNode get(char c) {
        return next[c - 'a'];
    }

    public void put(char c, TrieNode node) {
        next[c - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
