package container_with_most_water;

/**
 * Created by Xiaotian on 6/26/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;

        int n = height.length;
        int i = 0;
        int j = n - 1;
        int max = 0;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            max = Math.max(max, area);
            if (height[i] < height[j]) {
                i++;
            }
            else {
                j--;
            }
        }
        return max;
    }
}
