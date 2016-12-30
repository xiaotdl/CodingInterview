package jump_game_ii;

/**
 * Created by Xiaotian on 12/29/16.
 */
// TLE
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp[i]: min jumps from nums[0] to nums[i]
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}

// tag: greedy
// time: O(n)
// space: O(1)
class SolutionII {
    public int jump(int[] nums) {
        int jumps = 0, pos = 0, reach = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            reach = Math.max(reach, i + nums[i]);
            if (i == pos) {
                jumps++;
                pos = reach;
            }
        }
        return jumps;
    }
}
