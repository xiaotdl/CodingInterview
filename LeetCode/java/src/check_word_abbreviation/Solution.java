package check_word_abbreviation;

/**
 * Created by Xiaotian on 1/17/18.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    /*
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] S = word.toCharArray();
        char[] T = abbr.toCharArray();

        int i = 0;
        int j = 0;
        while (i < S.length && j < T.length) {
            if (Character.isDigit(T[j])) {
                if (T[j] == '0') return false; // leading 0 is illegal
                int num = 0;
                while (j < T.length && Character.isDigit(T[j])) {
                    num = 10 * num + T[j] - '0';
                    j++;
                }
                i += num;
            }
            else {
                if (S[i] != T[j]) return false;
                i++;
                j++;
            }
        }
        return i == S.length && j == T.length;
    }
}
