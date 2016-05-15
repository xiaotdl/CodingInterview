package roman_to_integer;

import java.util.HashMap;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    // tag: string, math
    // time: O(n), one pass through string
    // space: O(1), only used roman numeral to integer mapping
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);

        Integer result = 0;

        Character prevChar = s.charAt(0);
        Integer prevValue = hm.get(prevChar);
        for (int i = 1; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            if (hm.get(prevChar) < hm.get(currChar)) {
                prevValue = hm.get(currChar) - hm.get(prevChar);
            } else {
                result += prevValue;
                prevValue = hm.get(currChar);
            }
            prevChar = currChar;
        }
        if (prevValue > 0) {
            result += prevValue;
        }

        return result;
    }
}
