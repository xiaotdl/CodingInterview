package roman_to_integer;

import java.util.*;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    // Add all Roman char values except for IV(4), IX(9) alike numbers,
    // in which case we minus char value if char followed by bigger char, else add char value.
    // val += prevChar < currChar ? -prevVal : prevVal;
    // tag: str, math
    // time: O(n), one pass through string
    // space: O(1), only used roman numeral to integer mapping
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int result = 0;
        Character prevChar = s.charAt(0);
        int prevVal = m.get(prevChar);
        for (int i = 1; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            result += m.get(prevChar) < m.get(currChar) ? -prevVal : prevVal;
            prevChar = currChar;
            prevVal = m.get(prevChar);
        }
        result += prevVal;
        return result;
    }
}
