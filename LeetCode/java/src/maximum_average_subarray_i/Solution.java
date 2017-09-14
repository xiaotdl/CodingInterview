package maximum_average_subarray_i;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // sliding window
    // tag: array, ptr
    // time: O(n)
    // space: O(1)
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 0 || k > nums.length) return 0;

        int sum = 0;
        int l = 0;
        int r = k - 1;
        for (int i = l; i <= r; i++) {
            sum += nums[i];
        }
        int max = sum;
        while (r < nums.length - 1) {
            r++;
            sum = sum - nums[l] + nums[r];
            max = Math.max(max, sum);
            l++;
        }
        return (double) max / k;
    }
}

class SolutionII {
    // Same as Solution
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 0 || k > nums.length) return 0;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }
}
