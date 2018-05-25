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
        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
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

class SolutionV {
    // Same as SolutionIV, with backtracking path print out
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // dp[i]: maxLen LIS from nums[0..i] including nums[i]
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int[] bt = new int[nums.length];
        for (int i = 0; i < nums.length; i++) bt[i] = i;

        int maxLen = 1;
        int currMaxIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    prevMaxLen = Math.max(prevMaxLen, dp[j]);
                    bt[i] = j;
                }
            }
            dp[i] = prevMaxLen + 1;
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                currMaxIdx = i;
            }
        }

        // print out backtracking path
        List<Integer> path = new ArrayList<>();
        while (currMaxIdx >= 0) {
            path.add(0, nums[currMaxIdx]);
            if (currMaxIdx == bt[currMaxIdx]) break;
            currMaxIdx = bt[currMaxIdx];
        }
        System.out.println(path);

        return maxLen;
    }
}

class SolutionVI {
    // tag: dp, binary search
    // time: O(nlogn)
    // space: O(n)
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // minLast[i]: min last num when LIS is i
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            // find the first number in minLast >= nums[i]
            int idx = binarySearch(minLast, nums[i]);
            minLast[idx] = nums[i];
        }
        // System.out.println(Arrays.toString(minLast));

        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }

        return 0;
    }

    // find the first number >= target
    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }
        if (nums[l] >= target) return l;
        if (nums[r] >= target) return r;
        return -1;
    }
}
