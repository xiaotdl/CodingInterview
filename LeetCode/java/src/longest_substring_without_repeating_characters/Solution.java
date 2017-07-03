package longest_substring_without_repeating_characters;

import java.util.*;

/**
 * Created by Xiaotian on 6/28/17.
 */
public class Solution {
    // sliding window
    // tag: str, hash
    // time: O(n), at most 2n steps for i and j
    // space: O(1)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] S = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < s.length()) {
            if (!set.contains(S[r])) {
                set.add(S[r]);
                max = Math.max(max, set.size());
                r++;
            }
            else {
                set.remove(S[l]);
                l++;
            }
        }
        return max;
    }
}