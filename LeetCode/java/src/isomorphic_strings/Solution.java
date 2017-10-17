package isomorphic_strings;

import java.util.*;

/**
 * Created by Xiaotian on 10/14/17.
 */
public class Solution {
    // map1: s.char => t.char
    // map2: t.char => s.char
    // if both true, s.char <=> t.char is one-to-one mapping.
    // tag: hash
    // time: O(n)
    // space: O(n)
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        for (int i = 0; i < S.length; i++) {
            // s.char => t.char
            if (s2t.containsKey(S[i])) {
                if (s2t.get(S[i]) != T[i]) {
                    return false;
                }
            } else {
                s2t.put(S[i], T[i]);
            }

            // t.char => s.char
            if (t2s.containsKey(T[i])) {
                if (t2s.get(T[i]) != S[i]) {
                    return false;
                }
            } else {
                t2s.put(T[i], S[i]);
            }
        }
        return true;
    }
}
