package one_edit_distance;

/**
 * Created by Xiaotian on 7/5/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1)); // replace one char
                }
                else if (s.length() < t.length()) {
                    return s.substring(i).equals(t.substring(i + 1)); // delete one char from t
                }
                else {
                    return s.substring(i + 1).equals(t.substring(i)); // delete one char from s
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}
