package word_ladder;

import java.util.*;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // tag: str, bfs
    // time: O(n)
    // space: O(n)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 1;

        Set<String> wordDict = new HashSet<>();
        for (String word : wordList) {
            wordDict.add(word);
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> seen = new HashSet<>();
        seen.add(beginWord);

        int length = 1;
        while (!q.isEmpty()) {
            length++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String currWord = q.poll();
                for (String nextWord : getNextWords(currWord, wordDict)) {
                    if (seen.contains(nextWord)) continue;
                    if (nextWord.equals(endWord)) {
                        return length;
                    }
                    seen.add(nextWord);
                    q.offer(nextWord);
                }
            }
        }
        return 0;
    }

    private String replace(String word, int index, char c) {
        char[] chars = word.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    private ArrayList<String> getNextWords (String word, Set<String> wordDict) {
        ArrayList<String> nextWords = new ArrayList<>();
        for (char c = 'a'; c < 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) continue;
                String nextWord = replace(word, i, c);
                if (wordDict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}
