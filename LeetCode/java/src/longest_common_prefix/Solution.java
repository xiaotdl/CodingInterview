package longest_common_prefix;

/**
 * Created by Xiaotian on 5/16/16.
 */
public class Solution {
    // vertical scanning
    // tag: str, ptr
    // time: O(n), one pass through strings, worst case n * m (strs * avg str length )
    // space: O(1), no additional space used
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
