package jump_game;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class Solution {
    // TLE
    // tag: array, dp
    // time: O(n^2)
    // space: O(n)
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;

        // dp[i]: canJump from nums[0] to nums[i]
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }
}

class SolutionII {
    // tag: array, greedy
    // time: O(n)
    // space: O(1)
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false;
            }
            reach = Math.max(reach, nums[i] + i);
        }
        return true;
    }
}
