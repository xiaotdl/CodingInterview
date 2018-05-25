package construct_string_from_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 7/12/17.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    // tag: str, divide and conquer
    // time: O(n), n: number of tree nodes
    // space: O(1)
    public String tree2str(TreeNode t) {
        if (t == null) return "";

        String res = Integer.toString(t.val);
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if (left == "" && right == "") return res;
        else if (left == "") return res + "()" + "(" + right + ")";
        else if (right == "") return res + "(" + left + ")";
        return res + "(" + left + ")" + "(" + right + ")";
    }
}

class SolutionII {
    // tag: stack
    // time: O(n)
    // space: O(n)
    public String tree2str(TreeNode t) {
        if (t == null) return "";

        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (visited.contains(top)) {
                stack.pop();
                sb.append(")");
                continue;
            }
            visited.add(top);
            sb.append("(" + top.val);

            if (top.left == null && top.right == null) continue;
            if (top.left == null && top.right != null) sb.append("()");
            if (top.right != null) stack.push(top.right);
            if (top.left != null) stack.push(top.left);
        }
        return sb.substring(1, sb.length() - 1); // strip the outter parenthesis
    }
}
