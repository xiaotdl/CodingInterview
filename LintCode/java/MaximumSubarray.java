import java.util.ArrayList;

public class MaximumSubarray {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */

    // V1, O(n), O(1)
    // PrefixSum
    public int maxSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int sum = 0;
        int minSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);

        }

        return maxSum;
    }

    // V2, O(n), O(n)
    // DP(Sequence)
    // 典型的DP题：
    // 1. 状态dp[i]：以A[i]为最后一个数的所有max subarray的和。
    // 2. 通项公式：dp[i] = dp[i-1]<=0 ? dp[i] : dp[i-1]+A[i]
    // 3. 由于dp[i]仅取决于dp[i-1]，所以可以仅用一个变量来保存前一个状态，而节省内存。
    public int maxSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int[] maxSumWithLastNum = new int[nums.size()];
        maxSumWithLastNum[0] = nums.get(0);
        int maxSum = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            maxSumWithLastNum[i] = nums.get(i) + Math.max(0, maxSumWithLastNum[i - 1]);
            maxSum = Math.max(maxSum, maxSumWithLastNum[i]);
        }

        return maxSum;
    }

    // V3, O(n), O(1)
    // DP follow up
    public int maxSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int maxSumWithLastNum = nums.get(0);
        int maxSum = nums.get(0);

        for(int i = 1; i < nums.size(); i++){
            maxSumWithLastNum = nums.get(i) + Math.max(0, maxSumWithLastNum);
            maxSum = Math.max(maxSum, maxSumWithLastNum);
        }

        return maxSum;
    }
}

