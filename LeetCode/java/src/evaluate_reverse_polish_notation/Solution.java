package evaluate_reverse_polish_notation;

import java.util.*;

/**
 * Created by Xiaotian on 1/24/18.
 */
public class Solution {
    // 栈在表达式求值中的运用
    // • 遇到数字直接push进栈
    // • 遇到+ - * /时pop出栈顶2个数并相应运算求值，结果push进栈
    // tag: stack, math
    // time: O(n)
    // space: O(n)
    /*
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        Set<Character> ops = new HashSet<>();
        ops.add('+');
        ops.add('-');
        ops.add('*');
        ops.add('/');

        for (String token : tokens) {
            if (token.length() == 1 && ops.contains(token.charAt(0))) {
                char c = token.charAt(0);
                int r = stack.pop();
                int l = stack.pop();
                if (c == '+') {
                    stack.push(l + r);
                }
                else if (c == '-') {
                    stack.push(l - r);
                }
                else if (c == '*') {
                    stack.push(l * r);
                }
                else if (c == '/') {
                    stack.push(l / r);
                }
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
