package mini_parser;

/**
 * Created by Xiaotian on 7/9/17.
 */

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    // tag: str, ptr, stack
    // time: O(n)
    // space: O(n)
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger curr = null;
        int l = 0; // points to the start of a num substring
        int r = 0; // points to the end+1 of a num substring
        while (r < s.length()) {
            char c = s.charAt(r);
            if (c == '[') {
                if (curr != null) {
                    stack.push(curr);
                }
                curr = new NestedInteger();
                l = r + 1;
            }
            else if (c == ']') {
                String num = s.substring(l, r);
                if (num.length() != 0) {
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                if (!stack.isEmpty()) {
                    NestedInteger prev = stack.pop();
                    prev.add(curr);
                    curr = prev;
                }
                l = r + 1;
            }
            else if (c == ',') {
                if (s.charAt(r - 1) != ']') {
                    String num = s.substring(l, r);
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = r + 1;
            }
            r++;
        }
        return curr;
    }
}

class NestedInteger {
    // placeholder
    public NestedInteger() {};
    public NestedInteger(int value) {};
    public void add(NestedInteger ni) {};
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class SolutionII {
    // String contains only digits '0-9' '[' '-' ',' ']'.
    // e.g. s = "[123,[456,[789]]]"
    // upon '[': push parent list into stack if any, new a curr list
    // upon ',': 1) prev is a num, then add to the curr list, 2) prev is a list, NOOP
    // upon '-' || '0~9': add to num
    // upon ']': 1) prev is a num, ']' closes curr list, 2) prev is a list, ']' closes parent list
    // tag: str, ptr, stack
    // time: O(n)
    // space: O(n)
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>(); // stores parent lists, which is previous layer
        NestedInteger list = null; // points to the curr list
        String num = "";
        for (char c : s.toCharArray()) {
            if (c == '[') {
                if (list != null) {
                    stack.push(list); // list become parentList when pushed into stack
                }
                list = new NestedInteger();
            }
            else if (c == '-' || ('0' <= c && c <= '9')) {
                num += c;
            }
            else if (c == ',') {
                // prev is a num
                if (!num.equals("")) {
                    list.add(new NestedInteger(Integer.parseInt(num)));
                    num = "";
                }
                // prev is a list, it's been processed by ']', thus NOOP
            }
            else if (c == ']') {
                // prev is a num, ']' closes curr list
                if (!num.equals("")) {
                    list.add(new NestedInteger(Integer.parseInt(num)));
                    num = "";
                }
                // prev is a list, ']' closes parent list
                if (!stack.isEmpty()) {
                    NestedInteger parentList = stack.pop();
                    parentList.add(list);
                    list = parentList; // parentList become list when pop out of stack
                }
            }
        }
        if (!num.equals("")) {
            return new NestedInteger(Integer.parseInt(num));
        }
        return list;
    }
}
