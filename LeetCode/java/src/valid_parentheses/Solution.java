package valid_parentheses;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: string, stack
    // time: O(n), one pass through string
    // space: O(n), used stack to save chars
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Set<Character> leftParentheses = new HashSet<Character>(Arrays.asList('(', '[', '{'));
        Set<Character> rightParentheses = new HashSet<Character>(Arrays.asList(')', ']', '}'));

        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            if (leftParentheses.contains(currChar)) {
                st.push(currChar);
            } else if (rightParentheses.contains(currChar)) {
                if (st.empty()) return false;
                Character prevChar = st.peek();
                if (prevChar == '(' && currChar == ')') {
                    st.pop();
                } else if (prevChar == '[' && currChar == ']') {
                    st.pop();
                } else if (prevChar == '{' && currChar == '}') {
                    st.pop();
                } else {
                    return false;
                }
            }
        }

        if (!st.empty()) return false;

        return true;
    }
}
