package valid_parentheses;

import java.util.*;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: str, stack
    // time: O(n), one pass through string
    // space: O(n), used stack to save chars
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            }
            else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

class SolutionII {
    // 经典栈应用问题，从左往右扫，左括号对应入栈 ，相匹配右括号对应出栈
    // 注意三种不匹配的情况：
    //   – a. 扫完后栈中还有元素
    //   – b. 扫描过程中栈是空的但还要执行出栈操作
    //   – c. 要执行出栈但是括号不匹配，比如 [)
    // tag: str, stack
    // time: O(n)
    // space: O(n)
    /*
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        Map<Character, Character> pair = new HashMap<>();
        pair.put('(', ')');
        pair.put('[', ']');
        pair.put('{', '}');

        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character leftParenthesis = stack.pop();
                if (c != pair.get(leftParenthesis)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
