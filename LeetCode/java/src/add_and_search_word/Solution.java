package add_and_search_word;

/**
 * Created by Xiaotian on 9/18/17.
 */
public class Solution {
    // tag: trie
    // time:
    //   insert: O(m), m: length of word
    //   search: O(m)
    // space: O(m)
}
class WordDictionary {

    class TrieNode {
        char c;
        TrieNode[] next;
        boolean hasWord;

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
                if (this.hasWord) {
                    return this;
                };
                return null;
            }

            char c = word.charAt(pos);

            if (c == '.') {
                TrieNode node = null;
                for (int i = 0; i < 26; i++) {
                    if (next[i] == null) continue;
                    node = next[i].search(word, pos + 1);
                    if (node != null && node.hasWord) return node;
                }
                return null;
            }
            else {
                int i = c - 'a';
                if (next[i] == null) {
                    return null;
                }
                return next[i].search(word, pos + 1);
            }
        }
    }

    private TrieNode root = new TrieNode(' ');

    // Adds a word into the data structure.
    public void addWord(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.search(word, 0) != null;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
