package word_break;

import java.util.*;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(1)
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) return false;

        return dfs(s, wordDict, 0, new HashSet<Integer>());
    }

    boolean dfs(String s, Set<String> wordDict, int pos, Set<Integer> set) {
        if (pos == s.length()) return true;
        if (set.contains(pos)) return false;

        for (int i = pos; i < s.length(); i++) {
            String subStr = s.substring(pos, i + 1);
            if (wordDict.contains(subStr)) {
                if (dfs(s, wordDict, i + 1, set)) return true;
                else set.add(i);
            }
        }
        set.add(pos);
        return false;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        if (wordDict == null || wordDict.size() == 0) return false;

        // dp[i]: wordBreak(""||s[0..i-1], wordDict)
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

