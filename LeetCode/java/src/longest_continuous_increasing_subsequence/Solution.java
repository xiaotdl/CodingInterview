package longest_continuous_increasing_subsequence;

/**
 * Created by Xiaotian on 9/14/17.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int prev = nums[0];
        int len = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            if (curr > prev) {
                len++;
                max = Math.max(max, len);
            }
            else {
                len = 1;
            }
            prev = nums[i];
        }
        return max;
    }
}
