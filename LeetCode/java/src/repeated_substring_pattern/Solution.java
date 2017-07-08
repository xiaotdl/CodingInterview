package repeated_substring_pattern;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str, math
    // time: O(n)
    // space: O(n)
    public boolean repeatedSubstringPattern(String s) {
        int l = s.length();
        for (int i = l / 2; i >= 1; i--) {
            if (l % i == 0) {
                String subStr = s.substring(0, i);
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < l / i; j++) {
                    sb.append(subStr);
                }
                if (sb.toString().equals(s)) return true;
            }
        }
        return false;
    }
}
