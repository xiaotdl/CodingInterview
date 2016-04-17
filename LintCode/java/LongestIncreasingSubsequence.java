/**
 * Created by Xiaotian on 7/1/15.
 */
public class LongestIncreasingSubsequence {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */

    // V1, O(n^2)
    // DP(sequence)
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] f = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
                max = Math.max(max, f[i]);
            }
        }

        return max;
    }
}


