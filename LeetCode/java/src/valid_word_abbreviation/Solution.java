package valid_word_abbreviation;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (Character.isLetter(abbr.charAt(j)) || abbr.charAt(j) == '0') return false;
            int start = j;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) j++;
            int num = Integer.parseInt(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }
}
