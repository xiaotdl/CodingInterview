package partition_equal_subset_sum;

import java.util.*;

/**
 * Created by Xiaotian on 4/27/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    // backpack problem
    // tag: dp
    // time: m*n, n: len(nums), m: sum/2
    // space: m*n
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;

        int n = nums.length;
        int m = sum / 2;
        // dp[i][j]: able to pick a few from first i nums that sums to j
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) dp[i][0] = true;
        for (int j = 1; j < m + 1; j++) dp[0][j] = false;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j] // not pick ith num
                        || (j >= nums[i - 1] && dp[i - 1][j - nums[i - 1]]); // pick ith num
            }
        }
        return dp[n][m];
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    // backpack problem
    // tag: dp
    // time: m*n, n: len(nums), m: sum/2
    // space: m
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;

        int m = sum / 2;

        // dp[j]: able to pick a few from first nums that sums to j
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = m; j > 0; j--) {
                dp[j] = dp[j]
                     || (j >= num && dp[j - num]);
            }
        }
        return dp[m];
    }
}

class SolutionIII {
    // tag: dfs, backtrack
    // time: O(2^(sum/2))
    // space: O(1)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;

        Arrays.sort(nums);
        return dfs(nums, sum/2, nums.length - 1);
    }

    private boolean dfs(int[] nums, int target, int pos) {
        if (pos == 0) return false;

        if (nums[pos] > target) return false;
        if (nums[pos] == target) return true;

        return dfs(nums, target - nums[pos], pos - 1) // pick curr
                || dfs(nums, target, pos - 1); // doesn't pick curr
    }
}
