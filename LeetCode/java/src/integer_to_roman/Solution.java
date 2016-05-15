package integer_to_roman;

import java.util.HashMap;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    // tag: string, math
    // time: O(n), one pass through number
    // space: O(1), only used integer to roman numeral mapping
    public String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            return "";
        }

        HashMap<Integer, Character> hm = new HashMap<Integer, Character>();
        hm.put(1, 'I');
        hm.put(5, 'V');
        hm.put(10, 'X');
        hm.put(50, 'L');
        hm.put(100, 'C');
        hm.put(500, 'D');
        hm.put(1000, 'M');

        StringBuilder sb = new StringBuilder();

        Integer digit = 1;
        while (num > 0) {
            Integer currDigit = num % 10;
            if (1 <= currDigit && currDigit <= 3) {
                for (int i = 0; i < currDigit; i++) {
                    sb.insert(0, hm.get(digit));
                }
            } else if (currDigit == 4) {
                sb.insert(0, hm.get(5 * digit));
                sb.insert(0, hm.get(digit));
            } else if (currDigit == 5) {
                sb.insert(0, hm.get(5 * digit));
            } else if (6 <= currDigit && currDigit <= 8) {
                for (int i = 0; i < currDigit - 5; i++) {
                    sb.insert(0, hm.get(digit));
                }
                sb.insert(0, hm.get(5 * digit));
            } else if (currDigit == 9) {
                sb.insert(0, hm.get(10 * digit));
                sb.insert(0, hm.get(digit));
            }
            digit *= 10;
            num /= 10;
        }

        return sb.toString();
    }
}
