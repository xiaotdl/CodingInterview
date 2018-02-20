package longest_word_in_dictionary;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // tag: sort, hash
    // time: O(nlogn)
    // space: O(n)
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> seen = new HashSet<>();
        String maxStr = "";
        for (String word : words) {
            if (word.length() == 1 || seen.contains(word.substring(0, word.length() - 1))) {
                if (word.length() > maxStr.length()) {
                    maxStr = word;
                }
                seen.add(word);
            }
        }
        return maxStr;
    }
}
