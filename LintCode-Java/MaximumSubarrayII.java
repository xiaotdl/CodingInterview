import java.util.ArrayList;

public class MaximumSubarrayII {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */

    // V1, O(n)
    // PrefixSum
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];
        int sum, minSum, maxSum;

        sum = 0;
        minSum = 0;
        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            sum += nums.get(i);
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);
            left[i] = maxSum;
        }

        sum = 0;
        minSum = 0;
        maxSum = Integer.MIN_VALUE;
        for (int i = size - 1; i >= 0; i--) {
            sum += nums.get(i);
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);
            right[i] = maxSum;
        }

        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++) {
            maxSum = Math.max(maxSum, left[i] + right[i + 1]);
        }

        return maxSum;
    }
}
