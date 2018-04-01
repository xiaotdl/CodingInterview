package min_stack;

import java.util.Stack;

/**
 * Created by Xiaotian on 9/20/17.
 */
public class Solution {
}
class MinStack {
    //  7       0
    //  1       0
    //  0       0
    //  2       2
    // stack  minStack
    // tag: stack
    // time:
    //   push: O(1)
    //   pop: O(1)
    //   top: O(1)
    //   min: O(1)
    // space: O(n)
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        }
        else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStackII {
    // Same as MinStack, space improved a bit, but space complexity doesn't change
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStackII() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        }
        else {
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
class MinStackIII {
    class DoulbeLinkedNode {
        int val;
        int min;
        DoulbeLinkedNode prev;
        DoulbeLinkedNode next;
        DoulbeLinkedNode(int val, int min) {
            this.val = val;
            this.min = min;
            prev = next = null;
        }
    }
    DoulbeLinkedNode head;
    DoulbeLinkedNode tail;
    int size;
    /** initialize your data structure here. */
    public MinStackIII() {
        head = new DoulbeLinkedNode(-1, -1);
        tail = new DoulbeLinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void push(int x) {
        size++;

        DoulbeLinkedNode top = tail.prev;
        int min = size == 1 ? x : Math.min(top.min, x);
        DoulbeLinkedNode node = new DoulbeLinkedNode(x, min);

        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
    }

    public void pop() {
        if (size == 0) return; // throw
        size--;

        DoulbeLinkedNode top = tail.prev;
        top.prev.next = top.next;
        top.next.prev = top.prev;
    }

    public int top() {
        if (size == 0) return -1; // throw

        DoulbeLinkedNode top = tail.prev;
        return top.val;
    }

    public int getMin() {
        if (size == 0) return -1; // throw

        return tail.prev.min;
    }
}

class MinStackIV {
    // implemented without stack
    // tag: stack
    // time:
    //   push: O(1)
    //   pop: O(1)
    //   top: O(1)
    //   min: O(1)
    // space: O(n)
    class Node {
        int val;
        int min;
        Node prev;
        Node(int val, int min) {
            this.val = val;
            this.min = min;
            prev = null;
        }
    }
    Node top;

    public MinStackIV() {
        top = null;
    }

    public void push(int x) {
        if (top == null) {
            top = new Node(x, x);
            return;
        }

        int min = Math.min(top.min, x);
        Node node = new Node(x, min);
        node.prev = top;
        top = node;
    }

    public void pop() {
        if (top == null) return; // throw

        top = top.prev;
    }

    public int top() {
        if (top == null) return -1; // throw

        return top.val;
    }

    public int getMin() {
        if (top == null) return -1; // throw

        return top.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
