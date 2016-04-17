import java.util.ArrayList;

/**
 * Created by Xiaotian on 7/14/15.
 */
public class MaximumSubarrayDiff {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */

    // V1, O(n), O(n)
    // PrefixSum
    public int maxDiffSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int size = nums.size();
        int[] leftMinSum = new int[size];
        int[] leftMaxSum = new int[size];
        int[] rightMinSum = new int[size];
        int[] rightMaxSum = new int[size];
        int sum, minSum1, maxSum1, minSum2, maxSum2;

        sum = 0;
        minSum1 = 0;
        maxSum1 = Integer.MIN_VALUE;
        maxSum2 = 0;
        minSum2 = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            sum += nums.get(i);

            maxSum1 = Math.max(maxSum1, sum - minSum1);
            minSum1 = Math.min(minSum1, sum);
            leftMaxSum[i] = maxSum1;

            minSum2 = Math.min(minSum2, sum - maxSum2);
            maxSum2 = Math.max(maxSum2, sum);
            leftMinSum[i] = minSum2;
        }

        sum = 0;
        minSum1 = 0;
        maxSum1 = Integer.MIN_VALUE;
        maxSum2 = 0;
        minSum2 = Integer.MAX_VALUE;
        for (int i = size - 1; i >= 0; i--) {
            sum += nums.get(i);

            maxSum1 = Math.max(maxSum1, sum - minSum1);
            minSum1 = Math.min(minSum1, sum);
            rightMaxSum[i] = maxSum1;

            minSum2 = Math.min(minSum2, sum - maxSum2);
            maxSum2 = Math.max(maxSum2, sum);
            rightMinSum[i] = minSum2;
        }

        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++) {
            maxDiff = Math.max(maxDiff, leftMaxSum[i] - rightMinSum[i + 1]);
            maxDiff = Math.max(maxDiff, -leftMinSum[i] + rightMaxSum[i + 1]);
        }

        return maxDiff;
    }
}

