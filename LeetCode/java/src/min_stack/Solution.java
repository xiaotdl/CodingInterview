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

        public Node(int val) {
            this.val = val;
        }
    }

    Node curr;

    /** initialize your data structure here. */
    public MinStackIII() {
        curr = null;
    }

    public void push(int x) {
        if (curr == null) {
            curr = new Node(x);
            curr.min = x;
        }
        else {
            Node next = new Node(x);
            next.min = Math.min(curr.min, x);
            next.prev = curr;
            curr = next;
        }
    }

    public void pop() {
        curr = curr.prev;
    }

    public int top() {
        return curr.val;
    }

    public int getMin() {
        return curr.min;
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
