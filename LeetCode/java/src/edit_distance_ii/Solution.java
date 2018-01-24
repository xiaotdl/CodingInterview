package edit_distance_ii;

/**
 * Created by Xiaotian on 1/23/18.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }

        // t.length >= s.length
        if (t.length() - s.length() > 1) {
            return false;
        }

        if (t.length() - s.length() == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return t.substring(i+1).equals(s.substring(i));
                }
            }
        }

        if (t.length() - s.length() == 0) {
            int diffCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    diffCnt++;
                }
            }
            return diffCnt == 1;
        }

        return true;
    }
}
