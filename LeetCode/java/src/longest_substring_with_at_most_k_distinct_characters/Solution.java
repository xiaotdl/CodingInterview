package longest_substring_with_at_most_k_distinct_characters;

import java.util.*;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(k)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;

        char[] S = s.toCharArray();
        Map<Character, Integer> m = new HashMap<>(); //char2cnt

        int max = 0;
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            if (m.containsKey(S[r])) {
                m.put(S[r], m.get(S[r]) + 1);
                max = Math.max(max, r - l + 1);
                r++;
            }
            else {
                if (m.size() < k) {
                    m.put(S[r], 1);
                    max = Math.max(max, r - l + 1);
                    r++;
                }
                else {
                    m.put(S[l], m.get(S[l]) - 1);
                    if (m.get(S[l]) == 0) m.remove(S[l]);
                    l++;
                }
            }
        }
        return max;
    }
}
