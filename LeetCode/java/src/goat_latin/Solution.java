package goat_latin;

import java.util.*;

/**
 * Created by Xiaotian on 4/30/18.
 */
class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    public String toGoatLatin(String S) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        StringBuilder sb = new StringBuilder();

        String[] tokens = S.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            char firstChar = Character.toLowerCase(tokens[i].charAt(0));
            if (vowels.contains(firstChar)) {
                sb.append(tokens[i]);
            }
            else {
                sb.append(tokens[i].substring(1) + tokens[i].charAt(0));
            }
            sb.append("ma");
            for (int j = 0; j <= i; j++) sb.append("a");
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

