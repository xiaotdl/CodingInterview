package word_pattern_ii;

import java.util.*;

/**
 * Created by Xiaotian on 4/4/18.
 */
class Solution {
    // tag: hashmap, dfs
    // time: O(?)
    // space: O(len(p))
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> p2s = new HashMap<>();
        Map<String, Character> s2p = new HashMap<>();
        return dfs(pattern, 0, str, 0, p2s, s2p);
    }

    private boolean dfs(String pattern, int i, String str, int j, Map<Character, String> p2s, Map<String, Character> s2p) {
        if (i == pattern.length() && j == str.length()) return true;
        if (i == pattern.length() || j == str.length()) return false;

        char p = pattern.charAt(i);
        if (p2s.containsKey(p)) {
            String s = p2s.get(p);
            if (!str.substring(j).startsWith(s)) return false;

            return dfs(pattern, i + 1, str, j + s.length(), p2s, s2p);
        }

        for (int k = j; k < str.length(); k++) {
            String s = str.substring(j, k + 1);
            if (s2p.containsKey(s)) continue;

            p2s.put(p, s);
            s2p.put(s, p);
            if (dfs(pattern, i + 1, str, k + 1, p2s, s2p)) {
                return true;
            };
            p2s.remove(p);
            s2p.remove(s);
        }
        return false;
    }
}
