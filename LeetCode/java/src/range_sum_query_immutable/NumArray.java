package range_sum_query_immutable;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class NumArray {
    // tag: dp
    // time: O(n)
    // space: O(n)
    int[] dp;
    public NumArray(int[] nums) {
        if (nums == null || nums.length ==0) return;
        // dp[i]: sum nums[0..i]
        dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    // time: O(1)
    // space: O(1)
    public int sumRange(int i, int j) {
        if (i < 0 || j > dp.length || i > j) return 0;
        return dp[j] - (i == 0 ? 0 : dp[i - 1]);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
