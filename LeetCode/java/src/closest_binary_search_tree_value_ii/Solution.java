package closest_binary_search_tree_value_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/14/16.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    @Override
    public String toString() {
        return "" + this.val;
    }
}

public class Solution {
    // tag: stack
    // time: O(n + k), inorder takes n, compare takes k
    // space: O(1)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty()) {
                res.add(s2.pop());
            }
            else if (s2.isEmpty()) {
                res.add(s1.pop());
            }
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)) {
                res.add(s1.pop());
            }
            else {
                res.add(s2.pop());
            }
        }

        return res;
    }

    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        if ((!reverse && root.val > target) || (reverse && root.val <= target)) return;
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}

class SolutionII {
    // Ref:
    // https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70586/Clean-k+O(log(n))-code-which-is-less-than-O(n)
    // https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70503/O(logN)-Java-Solution-with-two-stacks-following-hint
    // tag: stack
    // time: O(logn+k), O(logn) binary search target, O(k) iterator backward/prev and forward/next
    // space: O(1)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        TreeNode closestNode = findClosestNode(root, target);
        System.out.println("closestNode: " + closestNode);

        Stack<TreeNode> nextStack = new Stack<>();
        Stack<TreeNode> prevStack = new Stack<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr == closestNode) {
                nextStack.add(curr);
                prevStack.add(curr);
                break;
            }
            if (curr.val < closestNode.val) {
                prevStack.add(curr);
                curr = curr.right;
            }
            else {
                nextStack.add(curr);
                curr = curr.left;
            }
        }

        System.out.println("prevStack: " + prevStack);
        System.out.println("nextStack: " + nextStack);
        // pop the closest node
        getPrev(prevStack);
        getNext(nextStack);


        List<Integer> res = new ArrayList<>();
        res.add(closestNode.val);
        k--;
        if (k == 0) return res;

        TreeNode prev = getPrev(prevStack);
        TreeNode next = getNext(nextStack);
        while (prev != null && next != null && k > 0) {
            if (Math.abs(prev.val - target) < Math.abs(next.val - target)) {
                res.add(prev.val);
                prev = getPrev(prevStack);
            }
            else {
                res.add(next.val);
                next = getNext(nextStack);
            }
            k--;
        }
        while (k > 0 && prev != null) {
            res.add(prev.val);
            prev = getPrev(prevStack);
            k--;
        }
        while (k > 0 && next != null) {
            res.add(next.val);
            next = getNext(nextStack);
            k--;
        }
        Collections.sort(res);
        return res;
    }

    private TreeNode findClosestNode(TreeNode root, double target) {
        TreeNode closestNode = root;
        while (root != null) {
            closestNode = (Math.abs(root.val - target) < Math.abs(closestNode.val - target) ? root : closestNode);
            if (closestNode.val == target) return closestNode;
            root = (root.val > target) ? root.left : root.right;
        }
        return closestNode;
    }

    private TreeNode getPrev(Stack<TreeNode> stack) {
        if (stack.isEmpty()) return null;
        TreeNode curr = stack.pop();
        TreeNode next = curr.left;
        while (next != null) {
            stack.add(next);
            next = next.right;
        }
        return curr;
    }

    private TreeNode getNext(Stack<TreeNode> stack) {
        if (stack.isEmpty()) return null;
        TreeNode curr = stack.pop();
        TreeNode next = curr.right;
        while (next != null) {
            stack.add(next);
            next = next.left;
        }
        return curr;
    }

    public static void main(String[] args) {
//          //        15
//        //       10    20
//        //     5  12     30
        //         11 13
        TreeNode _15 = new TreeNode(15);

        TreeNode _10 = new TreeNode(10);
        _15.left = _10;
        TreeNode _20 = new TreeNode(20);
        _15.right  = _20;

        TreeNode _5 = new TreeNode(5);
        _10.left = _5;
        TreeNode _12 = new TreeNode(12);
        _10.right = _12;
        TreeNode _30 = new TreeNode(30);
        _20.right  = _30;

        TreeNode _11 = new TreeNode(11);
        _12.left = _11;
        TreeNode _13 = new TreeNode(13);
        _12.right = _13;

        System.out.println(new SolutionII().closestKValues(_15, 12, 5));
    }
}


