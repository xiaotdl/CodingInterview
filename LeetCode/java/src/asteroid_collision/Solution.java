package asteroid_collision;

import java.util.*;

/**
 * Created by Xiaotian on 3/31/18.
 */
class Solution {
    // tag: stack
    // time: O(n)
    // space: O(n)
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int curr : asteroids) {
            boolean currAlive = true;
            while (!stack.isEmpty() && currAlive && stack.peek() > 0 && curr < 0) {
                if (stack.peek() < -curr) {
                    stack.pop();
                }
                else if (stack.peek() == -curr) {
                    stack.pop();
                    currAlive = false;
                }
                else { // stack.peek() > -curr
                    currAlive = false;
                }
            }
            if (currAlive) stack.push(curr);
        }

        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
