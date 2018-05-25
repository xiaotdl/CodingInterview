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
    // replace by value
    // 思路：删除节点是二叉树中最难的操作，一般的删除策略是找右子树最小节点来替换该节点的值, 并递归地删除右子树最小节点
    // tag: bst
    // time: O(n)
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

class SolutionII {
    // replace by reference
    // tag: bst
    // time: O(n)
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
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // replace root with right min node
            TreeNode parent = null;        // right min's parent
            TreeNode newRoot = root.right; // right min
            while (newRoot.left != null){
                parent = newRoot;
                newRoot = newRoot.left;
            }
            if (parent == null){
                newRoot.left = root.left;
                return newRoot;
            }
            parent.left = newRoot.right;
            newRoot.left = root.left;
            newRoot.right = root.right;
            return newRoot;
        }
        return root;
    }
}
