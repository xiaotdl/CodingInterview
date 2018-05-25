package maximum_product_subarray;

/**
 * Created by Xiaotian on 12/28/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(n)
    // space: O(n)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // dp1[i]: maxProduct from nums[0..i] including nums[i]
        // dp2[i]: minProduct from nums[0..i] including nums[i]
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            dp1[i] = Math.max(Math.max(dp1[i - 1] * num, dp2[i - 1] * num), num);
            dp2[i] = Math.min(Math.min(dp1[i - 1] * num, dp2[i - 1] * num), num);
            max = Math.max(max, dp1[i]);
        }
        return max;
    }
}

class SolutionII {
    // 滚动数组优化空间
    // tag: array, dp
    // time: O(n)
    // space: O(1)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // dp1[i]: maxProduct from nums[0..i] including nums[i]
        // dp2[i]: minProduct from nums[0..i] including nums[i]
        int[] dp1 = new int[2];
        int[] dp2 = new int[2];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            dp1[i%2] = Math.max(Math.max(dp1[(i - 1)%2] * num, dp2[(i - 1)%2] * num), num);
            dp2[i%2] = Math.min(Math.min(dp1[(i - 1)%2] * num, dp2[(i - 1)%2] * num), num);
            max = Math.max(max, dp1[i%2]);
        }
        return max;
    }
}
