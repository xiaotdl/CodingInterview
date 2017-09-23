package decode_string;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: stack
    // time: O(n)
    // space: O(n)
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

class SolutionII {
    // use a stack to read backwards
    // tag: stack
    // time: O(n)
    // space: O(n)
    /*
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        Stack<Object> stack = new Stack<>();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (c == '[') {
                stack.push(Integer.valueOf(num));
                num = 0;
            }
            else if (c == ']') {
                String newStr = popStringBackwards(stack);
                Integer _num = (Integer) stack.pop();
                for (int i = 0; i < _num; i++) {
                    stack.push(newStr);
                }
            }
            else {
                stack.push(String.valueOf(c));
            }
        }
        return popStringBackwards(stack);
    }

    private String popStringBackwards(Stack<Object> in) {
        // pop string until get a number or empty
        Stack<Object> out = new Stack<>();
        while (!in.isEmpty() && in.peek() instanceof String) {
            out.push(in.pop());
        }

        StringBuilder sb = new StringBuilder();
        while(!out.isEmpty()) {
            sb.append((String) out.pop());
        }
        return sb.toString();
    }
}
