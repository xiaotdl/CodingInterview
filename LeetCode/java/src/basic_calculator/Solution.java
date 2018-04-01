package basic_calculator;

import java.util.Stack;

/**
 * Created by Xiaotian on 5/21/16.
 */
public class Solution {
    // tag: str, stack, math
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
        System.out.println(new Solution().calculate("1 * (2 + 3) + 4"));
    }
}

class SolutionII {
    // Same as Solution
    // use a stack to save:
    //   1) curr ()'s res
    //   2) next ()'s sign
    // before going into next ()
    // add prev sign*num upon each +, -, )
    // operators value: + => 1, - => -1
    // e.g.
    // 1 + ( 2 - (3 + 4)) - 5
    // [1,+,2,-], res = 7
    // [1,+], res = 2 - 7 = -5
    // [], res = 1 + (-5) = -4
    // [], res = -4 - 5 = -9

    // tag: str, stack, math
    // time: O(n), one pass
    // space: O(n), use a stack to save curr ()'s res and next ()'s sign
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>(); // saves states of each ()

        int res = 0;
        int num = 0;
        int sign = 1; // init as '+'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            else if (c == '+') { // add prev num to res && reset (sign, num)
                res += sign * num;
                num = 0;
                sign = 1;
            }
            else if (c == '-') { // add prev num to res && reset (sign, num)
                res += sign * num;
                num = 0;
                sign = -1;
            }
            else if (c == '(') { // cache res and sign for curr () to stack
                stack.push(res);
                stack.push(sign);
                // reset
                res = 0;
                num = 0;
                sign = 1;
            }
            else if (c == ')') { // add prev num to res && reset (sign, num) && add res with () to prev res
                res += sign * num;;
                num = 0;
                sign = 1;

                res = stack.pop() * res; // sign * res
                res += stack.pop(); // prevRes
            }
        }
        res += sign * num;
        return res;
    }
}

class SolutionIII {
    // Same as Solution
    // use a stack to save:
    //   1) curr ()'s res
    //   2) next ()'s sign
    // before going into next ()
    // add prev sign*num upon each num
    // operators value: + => 1, - => -1
    // e.g.
    // 1 + ( 2 - (3 + 4)) - 5
    // [1,+,2,-], res = 7
    // [1,+], res = 2 - 7 = -5
    // [], res = 1 + (-5) = -4
    // [], res = -4 - 5 = -9

    // tag: str, stack, math
    // time: O(n), one pass
    // space: O(n), use a stack to save curr ()'s res and next ()'s sign
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        // use a stack to save curr ()'s res and next ()'s sign
        Stack<Integer> stack = new Stack<>();

        int res = 0;
        int num = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    i++;
                }

                // add (sign, num) to res
                res += sign * num;

                // reset num
                num = 0;
                continue;
            }

            if (c == '+') sign = 1;
            else if (c == '-') sign = -1;
            else if (c == '(') { // save curr ()'s res and next ()'s sign to stack
                stack.push(res);
                stack.push(sign);
                // reset
                res = 0;
                num = 0;
                sign = 1;
            }
            else if (c == ')') { // add curr ()'s res to prev ()'s res from stack
                res = stack.pop() * res + stack.pop(); // curr()'s sign * res + prev ()'s res
            }
        }
        return res;
    }
}
