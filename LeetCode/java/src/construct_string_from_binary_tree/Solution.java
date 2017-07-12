package construct_string_from_binary_tree;

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
