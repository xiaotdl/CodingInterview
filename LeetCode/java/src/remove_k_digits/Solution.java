package remove_k_digits;

import java.util.*;

/**
 * Created by Xiaotian on 2/13/18.
 */
public class Solution {
    // Go through the digits from left to right, remove previous digits if that makes the number smaller
    // We make number smaller by moving small number to the left
    // tag: stack
    // time: O(n)
    // space: O(n)
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k > num.length()) return "0";

        Stack<Character> stack = new Stack<>();

        int i = 0;
        while (i < num.length()) {
            // whenever meet a digit which is less than the previous digit, discard the previous one
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        while (k > 0) { // in case non-decreasing order, e.g. 1234, 1111
            stack.pop();
            k--;
        }

        String res = "";
        while (!stack.isEmpty()) res = stack.pop() + res;

        // skip leading 0
        while (res.length() > 0 && res.charAt(0) == '0') res = res.substring(1);
        return !res.equals("") ? res : "0";
    }
}

