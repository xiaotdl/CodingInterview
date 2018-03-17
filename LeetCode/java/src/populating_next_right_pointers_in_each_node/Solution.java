package populating_next_right_pointers_in_each_node;

/**
 * Created by Xiaotian on 3/15/18.
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left;
    TreeLinkNode right;
    TreeLinkNode next;
    TreeLinkNode(int x) { val = x; }
}

public class Solution {
    // tag: dfs
    // time: O(n)
    // space: O(1)
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        if (root.left != null) {
            root.left.next = root.right;
        }

        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
    }
}

class SolutionII {
    // lvl order traverse through .next pointer, connects childs at parent nodes
    // tag: iteration
    // time: O(n)
    // space: O(1)
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        TreeLinkNode lvlHead = root;
        while (lvlHead != null) {
            TreeLinkNode curr = lvlHead;
            while (curr != null) {
                if (curr.left != null) curr.left.next = curr.right;
                if (curr.right != null && curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }
            lvlHead = lvlHead.left;
        }
    }
}
