package sentence_similarity;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // tag: hash
    // time: O(n)
    // space: O(n)
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;

        Map<String, Set<String>> m = new HashMap<>(); // word2similarWord
        for (String[] p : pairs) {
            String w1 = p[0];
            String w2 = p[1];
            if (!m.containsKey(w1)) {
                m.put(w1, new HashSet<String>());
            }
            if (!m.containsKey(w2)) {
                m.put(w2, new HashSet<String>());
            }
            m.get(w1).add(w2);
            m.get(w2).add(w1);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (m.containsKey(words1[i]) && m.get(words1[i]).contains(words2[i])) continue;
            return false;
        }
        return true;
    }
}
