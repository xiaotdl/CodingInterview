package group_shifted_strings;

import java.util.*;

/**
 * Created by Xiaotian on 7/6/17.
 */
class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(n)
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0) return res;

        Map<String, List<String>> m = new HashMap<>(); // charDiffSeq2strs
        for (String s : strings) {
            String key = ""; //char diff sequence
            for (int i = 1; i < s.length(); i++) {
                key += String.format("%2d", (s.charAt(i) - s.charAt(i - 1) + 26) % 26);
            }
            m.putIfAbsent(key, new ArrayList<>());
            m.get(key).add(s);
        }
        return new ArrayList<List<String>>(m.values());
    }
}

