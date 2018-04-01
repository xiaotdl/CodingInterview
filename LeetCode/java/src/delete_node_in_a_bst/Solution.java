package delete_node_in_a_bst;

/**
 * Created by Xiaotian on 3/30/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // 思路：删除节点是二叉树中最难的操作，一般的删除策略是找右子树最小节点来替换该节点的值, 并递归地删除右子树最小节点
    // tag: bst
    // time: O(logn)
    // space: O(1)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        else { // root.val == key
            if (root.left != null && root.right != null) {
                int rightMin = findMin(root.right).val;
                root.val = rightMin;
                root.right = deleteNode(root.right, rightMin);
            }
            else {
                root = (root.left != null ? root.left : root.right);
            }
        }
        return root;
    }

    private TreeNode findMin(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root;
        return findMin(root.left);
    }
}
