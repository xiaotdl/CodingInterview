package integer_to_roman;

import java.util.*;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    // 0~3: I~III
    // 4:   IV
    // 5:   V
    // 6~8: VI~VIII
    // 9:   IX
    // 10:  X
    // ----------------
    // I, X, C: 1 * digit
    // V, L, D: 5 * digit
    // X, C, M: 10 * digit
    // ----------------
    // tag: str, math
    // time: O(n), one pass through number
    // space: O(1), only used integer to roman numeral mapping
    public String intToRoman(int num) {
        if (num < 1 || num > 3999) return "";

        Map<Integer, Character> m = new HashMap<>();
        m.put(1, 'I');
        m.put(5, 'V');
        m.put(10, 'X');
        m.put(50, 'L');
        m.put(100, 'C');
        m.put(500, 'D');
        m.put(1000, 'M');

        StringBuffer sb = new StringBuffer();
        int digit = 1;
        while (num > 0) {
            int d = num % 10; // currDigit
            if (1 <= d && d <= 3) {
                for (int i = 0; i < d; i++) sb.insert(0, m.get(1 * digit));
            }
            else if (d == 4) {
                sb.insert(0, m.get(5 * digit));
                sb.insert(0, m.get(1 * digit));
            }
            else if (d == 5) {
                sb.insert(0, m.get(5 * digit));
            }
            else if (6 <= d && d <= 8) {
                for (int i = 0; i < d - 5; i++) sb.insert(0, m.get(1 * digit));
                sb.insert(0, m.get(5 * digit));
            }
            else if (d == 9) {
                sb.insert(0, m.get(10 * digit));
                sb.insert(0, m.get(1 * digit));
            }
            num /= 10;
            digit *= 10;
        }
        return sb.toString();
    }
}

class SolutionII {
    // 如何数位分离？                %10 /10
    // （扩展）如何将一个数转成k进制？ %k  /k
    // tag: 数位分离
    // time: O(1)
    // space: O(1)
    /*
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] M = {"", "M", "MM", "MMM"};
        return M[n/1000] + C[(n/100) % 10] + X[(n/10) % 10] + I[n % 10];
    }
}
