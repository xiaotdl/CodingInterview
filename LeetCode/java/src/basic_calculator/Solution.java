package basic_calculator;

import java.util.Stack;

/**
 * Created by Xiaotian on 5/21/16.
 */
public class Solution {
    // tag: string, stack, math
    // time: O(n), one pass through the string
    // space: O(n), use a stack to save tmp results
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // cache previous result
                stack.push(result);
                stack.push(sign);
                // reset before going inside parenthesis
                result = 0;
                number = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                // reset after getting out of parenthesis
                number = 0;
                sign = 1;
                result *= stack.pop(); // the sign before the parenthesis
                result += stack.pop(); // the result before the parenthesis
            }
        }

        result += sign * number;

        return result;
    }

    public static void main (String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.calculate("1 - 1"));
    }
}
