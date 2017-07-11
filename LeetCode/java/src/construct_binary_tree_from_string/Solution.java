package construct_binary_tree_from_string;

import java.util.*;

/**
 * Created by Xiaotian on 7/10/17.
 */
// Input: "4(2(3)(1))(6(5))"
// Output: return the tree root node representing the following tree:

//        4
//      /   \
//     2     6
//    / \   /
//   3   1 5
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: str, stack, ptr
    // time: O(n)
    // space: O(n)
    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        int i = 0; // iterating ptr
        int j = 0; // points to start index of a number
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
            }
            else if ('0' <= c && c <= '9' || c == '-') {
                while (i + 1 < s.length() && '0' <= s.charAt(i + 1) && s.charAt(i + 1) <= '9') i++;
                TreeNode curr = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) {
                        parent.left = curr;
                    }
                    else {
                        parent.right = curr;
                    }
                }
                stack.push(curr);
            }
            i++;
            j = i;
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
