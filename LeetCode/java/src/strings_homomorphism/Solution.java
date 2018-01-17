package strings_homomorphism;

/**
 * Created by Xiaotian on 1/17/18.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        int[] s2t = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (s2t[S[i]] == 0) {
                s2t[S[i]] = T[i];
            }
            else {
                if (s2t[S[i]] != T[i]) {
                    return false;
                }
            }
        }

        int[] t2s = new int[256];
        for (int i = 0; i < t.length(); i++) {
            if (t2s[T[i]] == 0) {
                t2s[T[i]] = S[i];
            }
            else {
                if (t2s[T[i]] != S[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}