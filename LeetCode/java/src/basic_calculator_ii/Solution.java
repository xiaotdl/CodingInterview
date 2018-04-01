package basic_calculator_ii;

import java.util.*;

/**
 * Created by Xiaotian on 5/21/16.
 */
public class Solution {
    // init solution, TLE, can't pass all test cases
    // tag: str, ptr
    // time: O(n), three pass through string
    // space: O(n), parse string into token arrays
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        ArrayList<String> tokens = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            if (currChar == ' ') continue;
            if (currChar == '+' || currChar == '-' || currChar == '*' || currChar == '/') {
                tokens.add(sb.toString());
                sb = new StringBuilder();
                tokens.add(currChar.toString());
                continue;
            }
            sb.append(currChar);
        }
        tokens.add(sb.toString());

        int i;
        // calculate "*" and "/"
        i = 0;
        while (i < tokens.size()) {
            if (tokens.get(i).equals("*")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                tokens.set(i - 1, String.valueOf(a * b));
                tokens.remove(i);
                tokens.remove(i);
                continue;
            } else if (tokens.get(i).equals("/")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                tokens.set(i - 1, String.valueOf(a / b));
                tokens.remove(i);
                tokens.remove(i);
                continue;
            }
            i++;
        }

        // calculate "+" and "-"
        i = 0;
        while (i < tokens.size()) {
            if (tokens.get(i).equals("+")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                tokens.set(i - 1, String.valueOf(a + b));
                tokens.remove(i);
                tokens.remove(i);
                continue;
            } else if (tokens.get(i).equals("-")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                tokens.set(i - 1, String.valueOf(a - b));
                tokens.remove(i);
                tokens.remove(i);
                continue;
            }
            i++;
        }

        return Integer.parseInt(tokens.get(0));
    }

    public static void main (String[] args) {
//        new SolutionII().calculate("(1 + (2 * 3) - 4) / 3");
        System.out.println(new SolutionII().calculate("1*(2+3)*4"));
    }
}

class SolutionII {
    // Ref: https://leetcode.com/discuss/39454/accepted-infix-postfix-based-solution-explaination-600ms?show=39454#q39454
    // Ref: http://scriptasylum.com/tutorials/infix_postfix/algorithms/infix-postfix/
    // tag: str, stack
    // time: O(n), one pass through string
    // space: O(n), used two stacks to save num and operator tokens
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return evaluatePostfix(infixToPostfix(s));
    }

    List<Object> infixToPostfix(String s) {
        Stack<Character> operators = new Stack<Character>();
        List<Object> postfix = new ArrayList<Object>();

        int numberBuffer = 0;
        boolean bufferingOperand = false;
        for (char c: s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numberBuffer = numberBuffer * 10 + (c - '0');
                bufferingOperand = true;
            } else {
                if (bufferingOperand)
                    postfix.add(numberBuffer);
                numberBuffer = 0;
                bufferingOperand = false;

                if (c == ' ' || c == '\t'){
                    continue;
                } else if (c == '(') {
                    operators.push('(');
                } else if (c == ')') {
                    while (operators.peek() != '(')
                        postfix.add(operators.pop());
                    operators.pop(); // poping '('
                } else { // c == '+' || '-' || '*' || '/'
                    while (!operators.isEmpty() && priority(c) <= priority(operators.peek()))
                        postfix.add(operators.pop());
                    operators.push(c);
                }
            }

            System.out.println("postfix: " + postfix.toString());
            System.out.println("operators: " + operators.toString());
        }

        if (bufferingOperand)
            postfix.add(numberBuffer);

        while (!operators.isEmpty())
            postfix.add(operators.pop());

        System.out.println("postfix: " + postfix.toString());
        System.out.println("operators: " + operators.toString());

        return postfix;
    }

    int evaluatePostfix(List<Object> postfix) {
        Stack<Integer> operands = new Stack<Integer>();
        int a, b;
        for (Object s : postfix) {
            if (s instanceof Character) {
                char c = (Character) s;
                b = operands.pop();
                a = operands.pop();
                switch (c) {
                    case '+': operands.push(a + b); break;
                    case '-': operands.push(a - b); break;
                    case '*': operands.push(a * b); break;
                    case '/': operands.push(a / b); break;
                    default: break;
                }
            } else if (s instanceof Integer) {
                operands.push((Integer) s);
            } else {
                throw new RuntimeException("unexpected instance type!");
            }
        }

        return operands.pop();
    }

    int priority(char op) {
        switch (op) {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            case '(': return 0;
            case ')': return 0;
        }
        throw new RuntimeException("unknown op!");
    }
}

class SolutionIII {
    // credit: https://discuss.leetcode.com/topic/16935/share-my-java-solution
    // e.g.
    // 1 + 2 * 3 - 4 / 5
    // [1,2]
    // [1,2*3=6]
    // [1,6,-4]
    // [1,6,-4/5]
    // res = 1 + 6 + (-4/5) = 7

    // tag: str, stack
    // time: O(n), two pass, eval *,/ first, then +,-
    // space: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        // use a stack to save all nums to be +,- in second pass
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (i != s.length() - 1) continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                if (i != s.length() - 1) continue;
            }

            // add prev num to stack && reset (sign, num)
            if (sign == '+') {
                stack.push(num);
            }
            else if (sign == '-') {
                stack.push(-num);
            }
            else if (sign == '*') {
                stack.push(stack.pop() * num);
            }
            else if (sign == '/') {
                stack.push(stack.pop() / num);
            }

            sign = c;
            num = 0;
        }

        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }
}

class SolutionIV {
    // credit: Basic Calculator III, https://leetcode.com/problems/basic-calculator-iii/discuss/113600/Java-O(n)-Solution-Using-Two-Stacks
    // e.g.
    // 1 + 2 * 3 - 4 / 5
    // tag: str, stack
    // time: O(n), two pass, eval *,/ first, then +,-
    // space: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        // use a stack to save all nums to be +,- in second pass
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    i++;
                }

                // add (sign, num) to stack
                if (sign == '+') {
                    stack.push(num);
                }
                else if (sign == '-') {
                    stack.push(-num);
                }
                else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                // reset num
                num = 0;
                continue;
            }

            // update sign
            sign = c;
        }

        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }
}
