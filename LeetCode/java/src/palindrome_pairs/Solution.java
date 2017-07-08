package palindrome_pairs;

import java.util.*;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, hash
    // time: O(n^2*k), k: avg word length
    // space: O(n)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;

        Map<String, Integer> m = new HashMap<>(); // word2index
        for (int i = 0; i < words.length; i++) {
            m.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                // e.g.
                // word1=s1+s2
                // word2=reversed_s2
                // word2+word1=reversed_s2+s1+s2
                // in case s1 is palindrome, word2+word1 is palindrome
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);
                if (isPalindrome(s1)) {
                    String reversed_s2 = new StringBuffer(s2).reverse().toString();
                    if (m.containsKey(reversed_s2) && m.get(reversed_s2) != i) {
                        res.add(Arrays.asList(m.get(reversed_s2), i));
                    }
                }
                // word1=s1+s2; word2=reversed_s1
                // word1+word2=s1+s2+reversed_s1
                if (s2.length() != 0 && isPalindrome(s2)) {
                    String reversed_s1 = new StringBuffer(s1).reverse().toString();
                    if (m.containsKey(reversed_s1) && m.get(reversed_s1) != i) {
                        res.add(Arrays.asList(i, m.get(reversed_s1)));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] next;
    int index;
    List<Integer> list;

    TrieNode() {
        next = new TrieNode[26];
        index = -1;
        list = new ArrayList<>();
    }
}

class SolutionII {
    // credit: https://discuss.leetcode.com/topic/39585/o-n-k-2-java-solution-with-trie-structure-n-total-number-of-words-k-average-length-of-each-word/3
    // tag: str, trie
    // time: O(n*k^2), k: avg word length
    // space: O(n*k)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        int n = words.length;
        if (res == null || n == 0) return res;

        // build suffix trie
        TrieNode root = new TrieNode();
        for (int i = 0; i < n; i++) addWord(root, words[i], i);
        for (int i = 0; i < n; i++) search(words, i, root, res);
        return res;
    }

    // time: O(n*k^2)
    private void addWord(TrieNode root, String word, int wordIndex) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (root.next[c - 'a'] == null) root.next[c - 'a'] = new TrieNode();
            if (isPalindrome(word, 0, i)) root.list.add(wordIndex);
            root = root.next[c - 'a'];
        }
        root.list.add(wordIndex);
        root.index = wordIndex;
    }

    // time: O(n*k^2)
    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            char c = words[i].charAt(j);
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }
            root = root.next[c - 'a'];
            if (root == null) return;
        }
        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
