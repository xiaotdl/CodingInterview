package word_break;

import java.util.*;

/**
 * Created by Xiaotian on 12/30/16.
 */
class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) dict.add(word);
        Map<Integer, Boolean> memo = new HashMap<>();
        return dfs(dict, s, 0, memo);
    }

    private boolean dfs(Set<String> dict, String s, int pos, Map<Integer, Boolean> memo) {
        if (pos == s.length()) return true;
        if (memo.containsKey(pos)) return memo.get(pos);

        for (int i = pos; i < s.length(); i++) {
            String word = s.substring(pos, i + 1);
            if (!dict.contains(word)) continue;
            if (dfs(dict, s, i + 1, memo)) {
                memo.put(i, true);
                return true;
            }
        }
        memo.put(pos, false);
        return false;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        if (wordDict == null || wordDict.size() == 0) return false;

        Set<String> dict = new HashSet<>();
        for (String word : wordDict) dict.add(word);

        // dp[i]: wordBreak(""||s[0..i-1], dict)
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

