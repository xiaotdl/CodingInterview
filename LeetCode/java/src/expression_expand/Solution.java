package expression_expand;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    class Frame {
        String res;
        int num;
        public Frame(String res, int num) {
            this.res = res;
            this.num = num;
        }
    }

    // tag: stack
    // time: O(n)
    // space: O(n)
    public String expressionExpand(String s) {
        Stack<Frame> stack = new Stack<>();
        String res = "";
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                res += c;
                while (i + 1 < s.length() && Character.isLetter(s.charAt(i + 1))) {
                    res += s.charAt(i + 1);
                    i++;
                }
            }
            else if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            else if (c == '[') {
                stack.push(new Frame(res, num));
                res = "";
                num = 0;
            }
            else if (c == ']') {
                Frame frame = stack.pop();
                res = frame.res + multiplyStr(frame.num, res);
            }
        }
        return res;
    }

    private String multiplyStr(int num, String s) {
        String res = "";
        for (int i = 0; i < num; i++) {
            res += s;
        }
        return res;
    }
}
