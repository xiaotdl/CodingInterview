package decode_string;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
// tag: stack
// time: O(n)
// space: O(n)
public class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";

        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> sbStack = new Stack<>();
        int k = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            }
            else if (c == '[') {
                intStack.push(k);
                sbStack.push(sb);
                k = 0;
                sb = new StringBuilder();
            }
            else if (c == ']') {
                StringBuilder tmp = sb;
                k = intStack.pop();
                sb = sbStack.pop();
                while (k > 0) {
                    sb.append(tmp);
                    k--;
                }
            }
            else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
