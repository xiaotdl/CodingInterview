package house_robber;

/**
 * Created by Xiaotian on 7/20/16.
 */
public class Solution {
    // tag: dp
    // time: O(n), one iteration through nums.
    // space: O(n), one dimensional additional space.
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from nums[0..i-1]
        int[] dp = new int[nums.length + 1];

        dp[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            // rob last house
            int a = nums[i - 1] + (i - 2 >= 0 ? dp[i - 2] : 0);
            // doesn't rob last house
            int b = dp[i - 1];
            dp[i] = Math.max(a, b);
        }

        return dp[nums.length];
    }
}

class SolutionII {
    // same as SolutionI
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // dp[i]: max money robbed from house[0..i].
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}


class SolutionIII {
    // same as SolutionI
    // tag: dp
    // time: O(n)
    // space: O(n)
    /*
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) return 0;

        // dp[i]: maximumMoneyRobbed from {} || A[0..i-1]
        long[] dp = new long[A.length + 1];
        dp[0] = 0;
        dp[1] = A[0];
        for (int i = 2; i < A.length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }
        return dp[A.length];
    }
}

class SolutionIV {
    // Space improvement on SolutionIII
    // 滚动数组优化
    // tag: dp
    // time: O(n)
    // space: O(1)
    /*
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) return 0;

        // dp[i]: maximumMoneyRobbed from {} || A[0..i-1]
        long[] dp = new long[2];
        dp[0] = 0;
        dp[1] = A[0];
        for (int i = 2; i < A.length + 1; i++) {
            dp[i%2] = Math.max(dp[(i - 1)%2], dp[(i - 2)%2] + A[i - 1]);
        }
        return dp[A.length%2];
    }
}
