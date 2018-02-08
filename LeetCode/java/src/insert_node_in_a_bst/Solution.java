package insert_node_in_a_bst;

/**
 * Created by Xiaotian on 2/7/18.
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    // tag: binary tree, binary search
    // time: O(logn)
    // space: O(1)
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) return node;

        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        }
        else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}

