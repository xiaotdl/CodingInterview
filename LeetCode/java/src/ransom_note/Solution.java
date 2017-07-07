package ransom_note;

import java.util.*;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(1), fixed hash size < 26 chars
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        Map<Character, Integer> m = new HashMap<>(); //char2cnt
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            m.put(c, m.containsKey(c) ? m.get(c) + 1 : 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!m.containsKey(c)) return false;
            m.put(c, m.get(c) - 1);
            if (m.get(c) < 0) return false;
        }
        return true;
    }
}
