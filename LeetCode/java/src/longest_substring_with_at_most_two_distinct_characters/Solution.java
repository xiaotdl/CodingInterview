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
