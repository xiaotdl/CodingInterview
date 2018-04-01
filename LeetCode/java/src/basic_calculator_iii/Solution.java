package basic_calculator_iii;

import java.util.*;

/**
 * Created by Xiaotian on 3/27/18.
 */
public class Solution {
    // credit: https://leetcode.com/problems/basic-calculator-iii/discuss/113600/Java-O(n)-Solution-Using-Two-Stacks
    // credit: https://www.geeksforgeeks.org/expression-evaluation/
    // using two stacks: one for operands and one for operators.
    // e.g.
    // 1 + (2 - (3 + 4 * 5)) - 6
    // nums: [1, 2, 3, 4, 5], ops: [+, (, -, (, +, *]
    // nums: [1, 2, 3+4*5=23], ops: [+, (, -]
    // nums: [1, 2-23=-21], ops: [+]
    // nums: [1, -21, 6], ops: [+, -]
    // nums: [1-21=-20, 6], ops: [-]
    // nums: [-20-6=-26]

    // tag: str, stack
    // time: O(n)
    // space: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Integer> nums = new Stack<>(); // stores numbers
        Stack<Character> ops = new Stack<>(); // stores operators (including parentheses)

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                nums.push(num); // can't do math here as there are op precedence
                num = 0; // reset number
                continue;
            }

            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') {
                    nums.push(evalExpr(ops.pop(), nums.pop(), nums.pop())); // calculate *,/,+,-
                }
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && cmpPrecedence(ops.peek(), c)) {
                    nums.push(evalExpr(ops.pop(), nums.pop(), nums.pop())); // calculate *,/,+,-
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            nums.push(evalExpr(ops.pop(), nums.pop(), nums.pop())); // calculate +/-
        }
        return nums.pop();
    }

    private int evalExpr(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }

    // check if 'op1' has higher or same precedence as 'op2'
    private boolean cmpPrecedence(char op1, char op2) {
        if (op1 == '(' || op1 == ')') return false;
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) return false;
        return true;
    }
}
