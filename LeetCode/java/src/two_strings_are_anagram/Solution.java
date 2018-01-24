package two_strings_are_anagram;

/**
 * Created by Xiaotian on 1/24/18.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(1)
    /**
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] charCnt = new int[256];
        for (char c : s.toCharArray()) {
            charCnt[c]++;
        }
        for (char c : t.toCharArray()) {
            charCnt[c]--;
            if (charCnt[c] < 0) {
                return false;
            }
        }
        return true;
    }
};
