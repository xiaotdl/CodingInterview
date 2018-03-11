package max_stack;

import java.util.*;

/**
 * Created by Xiaotian on 3/5/18.
 */
public class Solution {
}

class EmptyStackException extends RuntimeException {}
class MaxStack {
    // two stack
    // space: O(n)
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    // O(1)
    public void push(int x) {
        stack.push(x);
        maxStack.push(!maxStack.isEmpty() ? Math.max(x, maxStack.peek()) : x);
    }

    // O(1)
    public int pop() {
        if (stack.isEmpty()) throw new EmptyStackException();
        maxStack.pop();
        return stack.pop();
    }

    // O(1)
    public int top() {
        if (stack.isEmpty()) throw new EmptyStackException();
        return stack.peek();
    }

    // O(1)
    public int peekMax() {
        if (stack.isEmpty()) throw new EmptyStackException();
        return maxStack.peek();
    }

    // O(n)
    public int popMax() {
        if (stack.isEmpty()) throw new EmptyStackException();
        Stack<Integer> buffer = new Stack<>();
        int max = peekMax();
        while (stack.peek() != max) {
            buffer.push(pop());
        }
        pop(); // pop max
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

class MaxStackII {
    // TreeMap<nodeVal, List<Node>> + Double LinkedList
    // space: O(n)
    class Node {
        int val;
        Node prev;
        Node next;
        Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map; // nodeVal2list(node)
    /** initialize your data structure here. */
    public MaxStackII() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        map = new TreeMap<>();
    }
    // O(logn)
    public void push(int x) {
        Node curr = new Node(x);
        curr.prev = tail.prev;
        curr.next = tail;
        tail.prev.next = curr;
        tail.prev = curr;
        map.putIfAbsent(curr.val, new ArrayList<Node>());
        map.get(curr.val).add(curr);
    }
    // O(logn)
    public int pop() {
        int val = tail.prev.val;
        List<Node> nodes = map.get(val);
        nodes.remove(nodes.size() - 1);
        if (nodes.size() == 0) {
            map.remove(val);
        }
        removeNode(tail.prev);
        return val;
    }
    // O(1)
    public int top() {
        return tail.prev.val;
    }
    // O(logn)
    public int peekMax() {
        return map.lastKey();
    }
    // O(logn)
    public int popMax() {
        int maxVal = map.lastKey();
        List<Node> nodes = map.get(maxVal);
        Node maxNode = nodes.remove(nodes.size() - 1);
        if (nodes.isEmpty()) {
            map.remove(maxVal);
        }
        removeNode(maxNode);
        return maxVal;
    }
    // O(1)
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

