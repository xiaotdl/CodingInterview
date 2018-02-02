package word_break_ii;

import java.util.*;

/**
 * Created by Xiaotian on 2/1/18.
 */
public class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(1)
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>(); //str2words
        return helper(s, wordDict, map);
    }

    private List<String> helper(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> res = new ArrayList<>();
        int n = s.length();

        if (n == 0) {
            return res;
        }

        for (int len = 1; len <= n; len++) {
            String word1 = s.substring(0, len);
            if (!dict.contains(word1)) continue;

            if (len == n) {
                res.add(word1);
            }
            else {
                String s2 = s.substring(len);
                List<String> wordsList = helper(s2, dict, memo);
                for (String words : wordsList) {
                    res.add(word1 + " " + words);
                }
            }
        }

        memo.putIfAbsent(s, res);
        return res;
    }
}
