package longest_common_prefix;

/**
 * Created by Xiaotian on 5/16/16.
 */
public class Solution {
    // tag: string, multiple pointers
    // time: O(n), one pass through strings
    // space: O(1), no additional space used
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs[0].length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < minLength(strs); i++) {
            char currChar = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != currChar) return sb.toString();
            }
            sb.append(currChar);
        }

        return sb.toString();
    }

    private int minLength(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        return minLength;
    }
}
