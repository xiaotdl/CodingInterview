package number_of_matching_subsequences;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: set
    // time: O(mn), m: len(S), n: len(words)
    // space: O(x), x: # of subseq of S, up to 2^len(S)
    public int numMatchingSubseq(String S, String[] words) {
        int cnt = 0;
        Set<Character> sChars = new HashSet<>();
        for (char c : S.toCharArray()) {
            sChars.add(c);
        }
        Set<String> matched = new HashSet<>();
        for (String word : words) {
            if (matched.contains(word)) {
                cnt++;
                continue;
            }

            // check is s contains all chars of word
            boolean missChar = false;
            for (char c : word.toCharArray()) {
                if (!sChars.contains(c)) missChar = true;
            }
            if (missChar) continue;

            if (isSubseq(word, S)) {
                matched.add(word);
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isSubseq(String subseq, String s) {
        int i = 0;
        int j = 0;
        while (i < subseq.length() && j < s.length()) {
            if (subseq.charAt(i) == s.charAt(j)) i++;
            j++;
        }
        return i == subseq.length();
    }
}
