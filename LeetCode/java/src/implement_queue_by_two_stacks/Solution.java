package implement_queue_by_two_stacks;

import java.util.*;

/**
 * Created by Xiaotian on 9/20/17.
 */
public class Solution {
}
class MyQueue {
    // tag: stack
    // time:
    //   push: O(1)
    //   pop: O(1)
    //   top: O(1)
    // space: O(n)
    private Stack<Integer> in;
    private Stack<Integer> out;
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        in.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }
}
