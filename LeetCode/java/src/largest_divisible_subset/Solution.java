package largest_divisible_subset;

import java.util.*;

/**
 * Created by Xiaotian on 12/28/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        // dp[i]: finds lenOfLDS including nums[i]
        // index[i]: the index of largest element that can be fully divided by nums[i];
        //           index[i] points to nums[i]'s largest factor in nums
        int[] dp = new int[nums.length];
        int[] index = new int[nums.length];
        dp[0] = 1;
        Arrays.fill(index, -1);

        int max = 0;
        int maxIndex = -1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        int i = maxIndex;
        while (i >= 0) {
            res.add(nums[i]);
            i = index[i];
        }

        Collections.sort(res);
        return res;
    }
}

class SolutionII {
    // tag: dp, backtracking
    // time: O(n^2)
    // space: O(n)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> path = new ArrayList<>();
        if (nums == null || nums.length == 0) return path;

        Arrays.sort(nums);

        int n = nums.length;
        // dp[i]: number of largestDivisibleSubset from nums[0..i]
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;
        int[] bt = new int[n];
        for (int i = 0; i < n; i++) bt[i] = i;

        int maxLen = 1;
        int maxLenIdx = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] == 0) continue;
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        bt[i] = j;
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxLenIdx = i;
            }
        }

        while (maxLenIdx >= 0) {
            path.add(0, nums[maxLenIdx]);
            if (maxLenIdx == bt[maxLenIdx]) break;
            maxLenIdx = bt[maxLenIdx];
        }

        return path;
    }
}
