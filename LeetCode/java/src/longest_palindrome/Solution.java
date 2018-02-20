package longest_palindrome;

import java.util.*;

/**
 * Created by Xiaotian on 2/18/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }

        int toRemove = set.size();
        if (toRemove > 0) toRemove--; // single char put in middle of the palindrome

        return s.length() - toRemove;
    }
}
