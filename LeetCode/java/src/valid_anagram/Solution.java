package valid_anagram;

/**
 * Created by Xiaotian on 3/28/18.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] charCnt = new int[26];
        for (char c : s.toCharArray()) {
            charCnt[c - 'a']++;
        }
        int cnt = 0;
        for (char c : t.toCharArray()) {
            if (charCnt[c - 'a'] > 0) {
                cnt++;
                charCnt[c - 'a']--;
            }
        }
        return cnt == s.length();
    }
}
