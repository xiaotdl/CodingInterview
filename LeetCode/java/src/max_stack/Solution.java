package max_stack;

import java.util.*;

/**
 * Created by Xiaotian on 3/5/18.
 */
public class Solution {
}

class EmptyStackException extends RuntimeException {}
class MaxStack {
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
