package largest_rectangle_in_histogram;

import java.util.Stack;

/**
 * Created by Xiaotian on 9/5/17.
 */
public class Solution {
    // TLE
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int l = 0; l < heights.length; l++) {
            int h = Integer.MAX_VALUE;
            for (int r = l; r < heights.length; r++) {
                h = Math.min(h, heights[r]);
                res = Math.max(res, (r - l + 1) * h);
            }
        }
        return res;
    }
}

class SolutionII {
    // use incremental stack to maintain surrounding smaller numbers
    // tag: array, stack
    // time: O(n)
    // space: O(n)
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && h <= heights[stack.peek()]) {
                int length = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, length * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
