package implement_trie;

import java.util.*;

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

class TrieII {
    // iterative version with TrieNode[] next
    // insert
    //   time: O(m), m: word length
    //   space: O(m), worst case: needs to create m TrieNodes when there is no such prefix
    // search
    //   time: O(m)
    //   space: O(1)
    // startswith
    //   time: O(m)
    //   space: O(1)
    class TrieNode {
        public char c;
        public TrieNode[] next;
        public boolean hasWord;

        public TrieNode(char c) {
            this.c = c;
            next = new TrieNode[26];
            hasWord = false;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public TrieII() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null ) {
                curr.next[c - 'a'] = new TrieNode(c);
            }
            curr = curr.next[c - 'a'];
        }
        curr.hasWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.hasWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.next[c - 'a'] == null) {
                return null;
            }
            curr = curr.next[c - 'a'];
        }
        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class TrieIII {
    // recursive version
    class TrieNode {
        public char c;
        public TrieNode[] next;
        public boolean hasWord;

        public TrieNode(char c) {
            this.c = c;
            next = new TrieNode[26];
            hasWord = false;
        }

        public void insert(String word, int pos) {
            if (pos == word.length()) {
                this.hasWord = true;
                return;
            }

            char c = word.charAt(pos);
            int i = c - 'a';
            if (next[i] == null) {
                next[i] = new TrieNode(c);
            }
            next[i].insert(word, pos + 1);
        }

        public TrieNode search(String word, int pos) {
            if (pos == word.length()) {
                return this;
            }

            char c = word.charAt(pos);
            int i = c - 'a';
            if (next[i] == null) {
                return null;
            }
            return next[i].search(word, pos + 1);
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public TrieIII() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.insert(word, 0);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root.search(word, 0);
        return node != null && node.hasWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root.search(prefix, 0);
        return node != null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

// iterative version with Map<Character, TrieNode> next
// Trie impl with iteration and hashmap
// insert: O(n)/O(n), n: word length
// search: O(n)/O(1)
// prefix: O(n)/O(1)
class TrieIV {

    class TrieNode {
        char c;
        boolean hasWord;
        Map<Character, TrieNode> next;
        TrieNode(char c) {
            this.c = c;
            hasWord = false;
            next = new HashMap<>();
        }
    }

    TrieNode root;
    public TrieIV() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            curr.next.putIfAbsent(c, new TrieNode(c));
            curr = curr.next.get(c);
        }
        curr.hasWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            curr = curr.next.get(c);
            if (curr == null) return false;
        }
        return curr.hasWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            curr = curr.next.get(c);
            if (curr == null) return false;
        }
        return true;
    }

    public TrieNode getNode(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            curr = curr.next.get(c);
            if (curr == null) return null;
        }
        return curr;
    }

    public List<String> autocomplete(String prefix) {
        List<String> res = new ArrayList<>();
        TrieNode curr = getNode(prefix);
        StringBuilder path = new StringBuilder();
        path.append(prefix);
        dfs(curr, path, res);
        return res;
    }

    // recursively search all children for words
    private void dfs(TrieNode curr, StringBuilder path, List<String> res) {
        if (curr == null) return;
        if (curr.hasWord) {
            res.add(path.toString());
        }

        for (char c : curr.next.keySet()) {
            path.append(c);
            dfs(curr.next.get(c), path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        TrieIV trie = new TrieIV();
        trie.insert("a");
        trie.insert("ab");
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abcdef");
        System.out.println(trie.autocomplete("a"));
    }
}
