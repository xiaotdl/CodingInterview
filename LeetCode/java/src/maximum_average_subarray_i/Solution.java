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
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;

        int l, r;
        for (l = 0, r = k; r < nums.length; l++, r++) {
            sum += nums[r] - nums[l];
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }
}
