package reverse_vowels_of_a_string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: string, two pointers
    // time: O(n), one pass through string
    // space: O(1), only used vowels hashset
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        char[] charArray = s.toCharArray();

        int i = 0;
        int j = charArray.length - 1;
        while (i < j) {
            while (i < j && !vowels.contains(charArray[i])) i++;
            while (i < j && !vowels.contains(charArray[j])) j--;
            swap(charArray, i, j);
            i++;
            j--;
        }

        return new String(charArray);
    }

    private void swap(char[] charArray, int i, int j) {
        char tmp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = tmp;
    }
}
