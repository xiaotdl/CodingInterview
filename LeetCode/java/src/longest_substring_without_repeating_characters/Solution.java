package longest_substring_without_repeating_characters;

import java.util.*;

/**
 * Created by Xiaotian on 6/28/17.
 */
public class Solution {
    // sliding window
    // tag: hash
    // time: O(n)
    // space: O(1)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int l = 0;
        int r = 0;
        char[] S = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (r < s.length()) {
            if (!set.contains(S[r])) {
                set.add(S[r]);
                r++;
                max = Math.max(max, set.size());
            }
            else {
                set.remove(S[l]);
                l++;
            }
        }
        return max;
    }
}
