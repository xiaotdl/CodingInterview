package populating_next_right_pointers_in_each_node_ii;

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
    // lvl order traverse through .next pointer, connects childs at parent nodes
    // dummyChild is needed to record first node of next lvl
    // tag: iteration
    // time: O(n)
    // space: O(1)
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummyChild = new TreeLinkNode(0);
        while (root != null) {
            TreeLinkNode currChild = dummyChild;
            while (root != null) {
                if (root.left != null) {
                    currChild.next = root.left;
                    currChild = currChild.next;
                }
                if (root.right != null) {
                    currChild.next = root.right;
                    currChild = currChild.next;
                }
                root = root.next;
            }
            root = dummyChild.next;
            dummyChild.next = null;
        }
    }
}
