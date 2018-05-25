package convert_binary_tree_to_circular_double_linked_list;

import java.util.*;

/**
 * Created by Xiaotian on 5/6/18.
 */
/*
Convert a binary tree into a circular doubly linked list.
The order of the elements in the linked list have to be the order of elements you get
when you do an in order traversal of the binary tree.
*/

class Node {
    int val;
    Node prev;
    Node next;
    Node (int val) {
        this.val = val;
        prev = next = null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int val) {
        this.val = val;
        left = right = null;
    }
}

class Solution {
    public Node btToCircularDLL(TreeNode root) {
        if (root == null) return null;

        Node dummy = new Node(0);
        Node tail = dummy;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();

            Node node = new Node(curr.val);
            tail.next = node;
            node.prev = tail;
            tail = node;

            curr = curr.right;
        }

        Node head = dummy.next;
        tail.next = head;
        head.prev = tail;
        return head;
    }

    public static void main(String[] args) {
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        _1.left = _2;
        _1.right = _3;
        _2.left = _4;
        _3.right = _5;

        Node head = new Solution().btToCircularDLL(_1);
        int k = 10;
        while (k-- > 0 && head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}










