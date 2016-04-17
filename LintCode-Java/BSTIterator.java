/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a tree:
 * Solution iterator = new Solution(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    private TreeNode curr;

    //@param root: The root of binary tree.
    public Solution(TreeNode root) {
        curr = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return (curr != null || !stack.isEmpty());
    }

    //@return: return next node
    public TreeNode next() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        curr = stack.pop();
        TreeNode node = curr;
        curr = curr.right;

        return node;
    }
}
