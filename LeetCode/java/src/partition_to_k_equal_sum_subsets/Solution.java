package partition_to_k_equal_sum_subsets;

import java.util.*;

/**
 * Created by Xiaotian on 4/10/18.
 */
class Solution {
    // O(k^n)&O(k), exhaustive search, dfs
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int pos = nums.length - 1;
        if (nums[pos] > target) return false;
        while (pos >= 0 && nums[pos] == target) {
            pos--;
            k--;
        }
        return search(nums, pos, new int[k], target);
    }

    public boolean search(int[] nums, int pos, int[] subsets, int target) {
        if (pos < 0) return true;
        int currNum = nums[pos];
        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] + currNum <= target) {
                subsets[i] += currNum;
                if (search(nums, pos - 1, subsets, target)) return true;
                subsets[i] -= currNum;
            }
            if (subsets[i] == 0) break;
        }
        return false;
    }
}

class SolutionII {
    // O(n∗2^n)&O(2^n), dp+memo
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];

        for (int state = 0; state < (1 << N); state++) {
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}
