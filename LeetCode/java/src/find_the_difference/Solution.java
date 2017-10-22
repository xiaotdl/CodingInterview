package find_the_difference;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            c ^= t.charAt(i);
        }
        return c;
    }
}
