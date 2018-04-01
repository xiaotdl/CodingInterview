package word_break_ii;

import java.util.*;

/**
 * Created by Xiaotian on 2/1/18.
 */
class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(1)
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>(); //str2words
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) dict.add(word);
        return dfs(s, dict, memo);
    }

    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) return res;

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        int n = s.length();
        for (int len = 1; len <= n; len++) {
            String word1 = s.substring(0, len);
            if (!dict.contains(word1)) continue;

            if (len == n) {
                res.add(word1);
            }
            else {
                String s2 = s.substring(len);
                List<String> wordsList = dfs(s2, dict, memo);
                for (String words : wordsList) {
                    res.add(word1 + " " + words);
                }
            }
        }

        memo.put(s, res);
        return res;
    }
}
