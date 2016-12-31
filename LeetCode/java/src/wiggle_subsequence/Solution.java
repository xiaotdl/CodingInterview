package wiggle_subsequence;

import java.util.*;

/**
 * Created by Xiaotian on 12/30/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int max = 0;
        int n = nums.length;
        // dp1[i]: wiggleMaxLength(nums[0..i]) including nums[i], ends with positive diff
        // dp2[i]: wiggleMaxLength(nums[0..i]) including nums[i], ends with negative diff
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        dp1[0] = 1;
        dp2[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] - nums[j] > 0) {
                    dp1[i] = Math.max(dp1[i], dp2[j] + 1);
                }
                else if (nums[i] - nums[j] < 0) {
                    dp2[i] = Math.max(dp2[i], dp1[j] + 1);
                }
            }
            max = Math.max(dp1[i], dp2[i]);
        }
        return max;
    }
}
