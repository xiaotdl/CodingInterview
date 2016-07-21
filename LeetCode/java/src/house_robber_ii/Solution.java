package house_robber_ii;

/**
 * Created by Xiaotian on 7/21/16.
 */
public class Solution {
    // tag: DP
    // time: O(n), two iterations through nums.
    // space: O(n), 2 x one dimensional additional space.
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from nums[0..i-1]

        // rob first house
        int[] dp1 = new int[nums.length + 1];
        dp1[0] = 0;
        dp1[1] = nums[0];
        for (int i = 2; i < nums.length + 1; i++) {
            int a = dp1[i - 1];               // doesn't rob last house
            int b = dp1[i - 2] + nums[i - 1]; // rob last house
            if (i == nums.length) {
                dp1[i] = a;
            } else {
                dp1[i] = Math.max(a, b);
            }
        }

        // doesn't rob first house
        int[] dp2 = new int[nums.length + 1];
        dp2[0] = 0;
        dp2[1] = 0;
        for (int i = 2; i < nums.length + 1; i++) {
            int a = dp2[i - 1];               // doesn't rob last house
            int b = dp2[i - 2] + nums[i - 1]; // rob last house
            dp2[i] = Math.max(a, b);
        }

        return Math.max(dp1[nums.length], dp2[nums.length]);
    }
}

class SolutionII {
    // very similiar to Solution
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        int length = end - start + 1;
        if (nums == null || length <= 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from nums[start..i-1]
        int[] dp = new int[length + 1];

        dp[0] = 0;
        for (int i = 1; i < length + 1; i++) {
            // rob last house
            int a = nums[start + i - 1] + (i - 2 >= 0 ? dp[i - 2] : 0);
            // doesn't rob last house
            int b = dp[i - 1];
            dp[i] = Math.max(a, b);
        }

        return dp[length];
    }
}
