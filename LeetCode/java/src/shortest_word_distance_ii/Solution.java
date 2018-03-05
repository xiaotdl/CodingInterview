package shortest_word_distance_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/2/18.
 */
public class Solution {
}

class WordDistance {
    // merge sort
    // tag: ptr, sort
    // time: O(n)
    // space: O(n)
    Map<String, List<Integer>> m; // word2idx(s)
    public WordDistance(String[] words) {
        m = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            m.putIfAbsent(words[i], new ArrayList<Integer>());
            m.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = m.get(word1);
        List<Integer> l2 = m.get(word2);
        int minDiff = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int a = 0, b = 0;
        while (i < l1.size() && j < l2.size()) {
            a = l1.get(i);
            b = l2.get(j);
            minDiff = Math.min(minDiff, Math.abs(a - b));
            if (a < b) i++;
            else j++;
        }
        return minDiff;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
