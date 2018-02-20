package longest_word_in_dictionary_through_deleting;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // tag: sort
    // time: O(nlogn)
    // space: O(n)
    public String findLongestWord(String s, List<String> dict) {
        Collections.sort(dict);
        String maxStr = "";
        for (String word : dict) {
            if (isSubsequence(word, s)) {
                if (word.length() > maxStr.length()) {
                    maxStr = word;
                }
            }
        }
        return maxStr;
    }

    private boolean isSubsequence(String subseq, String s) {
        int i = 0;
        int j = 0;
        while (i < subseq.length() && j < s.length()) {
            if (subseq.charAt(i) == s.charAt(j)) i++;
            j++;
        }
        return i == subseq.length();
    }
}
