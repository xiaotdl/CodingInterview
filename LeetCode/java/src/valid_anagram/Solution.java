package valid_anagram;

/**
 * Created by Xiaotian on 3/28/18.
 */
class Solution {
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

class SolutionII {
    // tag: str
    // time: O(n)
    // space: O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] charCnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCnt[s.charAt(i) - 'a']++;
            charCnt[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (charCnt[i] != 0) return false;
        }
        return true;
    }
}
