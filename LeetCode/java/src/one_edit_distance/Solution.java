package one_edit_distance;

/**
 * Created by Xiaotian on 7/5/17.
 */
class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < Math.min(S.length, T.length); i++) {
            if (S[i] == T[i]) continue;

            if (S.length == T.length) {
                return s.substring(i + 1).equals(t.substring(i + 1)); // replace one char
            }
            else if (S.length + 1 == T.length) {
                return s.substring(i).equals(t.substring(i + 1)); // delete one char from t
            }
            else { // S.length == T.length + 1
                return t.substring(i).equals(s.substring(i + 1)); // delete one char from s
            }
        }
        return Math.abs(s.length() - t.length()) == 1; // two strs might be equal
    }
}
