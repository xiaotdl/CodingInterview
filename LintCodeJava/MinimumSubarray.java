import java.util.ArrayList;

public class MinimumSubarray {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */

    // V1, O(n)
    // PrefixSum
    public int minSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int sum = 0;
        int maxSum = 0;
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            minSum = Math.min(minSum, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
        }

        return minSum;
    }
}
