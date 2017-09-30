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

class SolutionII {
    // tag: dp
    // time: O(n)
    // space: O(n)
    /*
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) return 0;

        return Math.max(
                helper(A),
                helper(reversed(A))
        );
    }

    public int helper(int[] A) {
        // dp[i]: longestIncreasingContinuousSubsequence ending with A[i]
        int[] dp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            dp[i] = 1;
        }
        int max = dp[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int[] reversed(int[] A) {
        int[] reversed = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            reversed[i] = A[A.length - 1 - i];
        }
        return reversed;
    }
}
