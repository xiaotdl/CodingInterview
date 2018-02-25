package target_sum;

/**
 * Created by Xiaotian on 2/17/18.
 */
public class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(1)
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        return dfs(nums, S, 0);
    }

    private int dfs(int[] nums, int S, int pos) {
        if (pos == nums.length) return S == 0 ? 1 : 0;

        int cnt = 0;
        cnt += dfs(nums, S - (+nums[pos]), pos + 1);
        cnt += dfs(nums, S - (-nums[pos]), pos + 1);
        return cnt;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n*sum), sum of all nums
    // space: O(n*sum)
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        if (sum < Math.abs(S)) {
            return 0;
        }

        //init for dp
        int doubleSum = sum << 1;
        int[][] dp = new int[nums.length][doubleSum + 1];
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum - nums[0]] = 1;
            dp[0][sum + nums[0]] = 1;
        }
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j <= doubleSum; j++) {
//                System.out.print(dp[i][j] + "  ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        //dp
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= doubleSum; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
                if (j + nums[i] <= doubleSum) {
                    dp[i][j] += dp[i - 1][j + nums[i]];
                }
            }
        }
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j <= doubleSum; j++) {
//                try
//                {
//                    Thread.sleep(100);
//                }
//                catch(InterruptedException ex)
//                {
//                    Thread.currentThread().interrupt();
//                }
//                System.out.print(dp[i][j] + "  ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        return dp[nums.length - 1][S + sum];
    }
}

