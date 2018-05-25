package word_pattern;

import java.util.*;

/**
 * Created by Xiaotian on 4/4/18.
 */
class Solution {
    // two way mapping
    // tag: hashmap
    // time: O(n)
    // space: O(n)
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> c2w = new HashMap<>(); // char2word
        Map<String, Character> w2c = new HashMap<>(); // word2char
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (c2w.containsKey(c)) {
                if (!c2w.get(c).equals(w)) return false;
            }
            else c2w.put(c, w);

            if (w2c.containsKey(w)) {
                if (w2c.get(w) != c) return false;
            }
            else w2c.put(w, c);
        }
        return true;
    }
}
