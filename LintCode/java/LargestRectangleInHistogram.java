import java.util.Stack;

public class LargestRectangleInHistogram {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */

    // V1, O(n^3)
    // TLE
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;

        for (int start = 0; start < height.length; start++) {
            for (int end = start; end < height.length; end++) {
                int width = end - start + 1;
                int length = Integer.MAX_VALUE;
                for (int i = start; i <= end; i++) {
                    length = Math.min(length, height[i]);
                }
                maxArea = Math.max(maxArea, length * width);
            }
        }

        return maxArea;
    }

    // V2, O(n^2)
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;

        for (int start = 0; start < height.length; start++) {
            int length = Integer.MAX_VALUE;
            for (int end = start; end < height.length; end++) {
                int width = end - start + 1;
                length = Math.min(length, height[end]);
                maxArea = Math.max(maxArea, length * width);
            }
        }

        return maxArea;
    }

    // V3, O(n)
    // Incremental Stack
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;

        for (int i = 0; i <= height.length; i++) {
            int curr = (i == height.length) ? 0 : height[i];

            while (!stack.isEmpty() && curr <= height[stack.peek()]) {
                int length = height[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, length * width);
            }

            stack.push(i);
        }


        return maxArea;
    }
}


