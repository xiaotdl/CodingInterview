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
