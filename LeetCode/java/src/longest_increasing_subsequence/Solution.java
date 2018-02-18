package longest_increasing_subsequence;

import java.util.*;

/**
 * Created by Xiaotian on 12/27/16.
 */
public class Solution {
    // TLE
    // tag: brutal force, dfs
    // time: O(2^n)
    // space: O(2^n)
    public int lengthOfLIS(int[] nums) {
        return dfs(nums, Integer.MIN_VALUE, 0);
    }

    private int dfs(int[] nums, int prevVal, int pos) {
        if (pos == nums.length) return 0;

        int len1 = 0;
        if (nums[pos] > prevVal) {
            len1 = dfs(nums, nums[pos], pos + 1) + 1; // with num at pos
        }
        int len2 = dfs(nums, prevVal, pos + 1); // without num at pos
        return Math.max(len1, len2);
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int n = nums.length;
        // dp[i]: lenOfLIS of nums[0..i] including nums[i]
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int lenOfLIS : dp) {
            max = Math.max(max, lenOfLIS);
        }
        return max;
    }
}

class SolutionIII {
    // tag: dp, binary search
    // time: O(nlogn)
    // space: O(n)
    public int lengthOfLIS(int[] nums) {
        // dp[i]: min num that subseq of length i+1 can end with, thus dp[] is not the LIS
        int[] dp = new int[nums.length];
        int lenOfLIS = 0;
        for(int num : nums) {
            int i = Arrays.binarySearch(dp, 0, lenOfLIS, num);
            if(i < 0) i = -(i + 1); // finds index of first larger
            dp[i] = num;
            if(i == lenOfLIS) lenOfLIS++;
        }
        return lenOfLIS;
    }
}

class SolutionIV {
    // Same as SolutionII
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // dp[i]: maxLen LIS from nums[0..i] including nums[i]
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    prevMaxLen = Math.max(prevMaxLen, dp[j]);
                }
            }
            dp[i] = prevMaxLen + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
