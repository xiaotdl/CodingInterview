package longest_substring_with_at_most_two_distinct_characters;

import java.util.*;

/**
 * Created by Xiaotian on 7/5/17.
 */
public class Solution {
    // sliding window
    // tag: str, hash, ptr
    // time: O(n)
    // space: O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> char2cnt = new HashMap<>();

        char[] S = s.toCharArray();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < S.length) {
            if (char2cnt.containsKey(S[r])) {
                char2cnt.put(S[r], char2cnt.get(S[r]) + 1);
                max = Math.max(max, r - l + 1);
                r++;
            }
            else {
                if (char2cnt.size() < 2) {
                    char2cnt.put(S[r], 1);
                    max = Math.max(max, r - l + 1);
                    r++;
                }
                else {
                    char2cnt.put(S[l], char2cnt.get(S[l]) - 1);
                    if (char2cnt.get(S[l]) == 0) char2cnt.remove(S[l]);
                    l++;
                }
            }
        }
        return max;
    }
}

class SolutionII {
    // sliding window
    // tag: str, hash, ptr
    // time: O(n)
    // space: O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> m = new HashMap<>(); // char2cnt;
        char[] S = s.toCharArray();
        int maxLen = 0;
        int l, r;
        for (l = 0, r = 0; r < S.length; l++) {
            while (r < S.length) {
                if (m.containsKey(S[r])) {
                    m.put(S[r], m.get(S[r]) + 1);
                }
                else {
                    if (m.size() < 2) {
                        m.put(S[r], 1);
                    }
                    else {
                        break;
                    }
                }
                r++;
            }
            maxLen = Math.max(maxLen, r - l);
            m.put(S[l], m.get(S[l]) - 1);
            if (m.get(S[l]) == 0) m.remove(S[l]);
        }
        return maxLen;
    }
}

class SolutionIII {
    // sliding window
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] charCnt = new int[256];
        int distinctCnt = 0;
        char[] S = s.toCharArray();
        int maxLen = 0;
        for (int i = 0, j = 0; i < S.length; i++) {
            while (j < S.length && (charCnt[S[j]] > 0 || distinctCnt < 2)) {
                if (charCnt[S[j]] == 0) distinctCnt++;
                charCnt[S[j]]++;
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
            charCnt[S[i]]--;
            if (charCnt[S[i]] == 0) distinctCnt--;
        }
        return maxLen;
    }
}
