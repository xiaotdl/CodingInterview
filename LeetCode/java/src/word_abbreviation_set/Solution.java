package word_abbreviation_set;

import java.util.*;

/**
 * Created by Xiaotian on 1/24/18.
 */
public class Solution {
}

class ValidWordAbbr {
    Map<String, Integer> dict; // word2cnt
    Map<String, Integer> abbr; // abbr2cnt
    /*
    * @param dictionary: a list of words
    */
    public ValidWordAbbr(String[] dictionary) {
        dict = new HashMap<>();
        abbr = new HashMap<>();
        for (String word : dictionary) {
            dict.put(word, (!dict.containsKey(word) ? 0 : dict.get(word)) + 1);
//            dict.put(word, dict.getOrDefault(word, 0) + 1);
            String wordAbbr = getAbbr(word);
            abbr.put(wordAbbr, (!abbr.containsKey(wordAbbr) ? 0 : abbr.get(wordAbbr)) + 1);
//            abbr.put(wordAbbr, abbr.getOrDefault(wordAbbr, 0) + 1);
        }
    }

    /*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        return dict.get(word) == abbr.get(getAbbr(word));
    }

    public String getAbbr(String word) {
        if (word.length() <= 2) {
            return word;
        }
        return "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() -1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param = obj.isUnique(word);
 */
