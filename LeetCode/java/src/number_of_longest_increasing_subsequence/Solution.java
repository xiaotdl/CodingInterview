package number_of_longest_increasing_subsequence;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] len = new int[n]; // len[i]: maxLen of LIS from nums[0..i] ending with nums[i]
        int[] cnt = new int[n]; // cnt[i]: maxCnt of LIS from nums[0..i] ending with nums[i]
        len[0] = 1;
        cnt[0] = 1;
        int maxLen = 1;
        int maxCnt = 1;
        for (int i = 1; i < n; i++) {
            cnt[i] = 1;
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[j] > prevMaxLen) {
                        prevMaxLen = len[j];
                        cnt[i] = cnt[j];
                    }
                    else if (len[j] == prevMaxLen) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            len[i] = prevMaxLen + 1;
            if (len[i] > maxLen) {
                maxLen = len[i];
                maxCnt = cnt[i];
            }
            else if (len[i] == maxLen) {
                maxCnt += cnt[i];
            }
        }
        return maxCnt;
    }
}

class SolutionII {
    // Same as Solution
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // len[i]: maxLenOfLIS(nums[0..i]) ending with nums[i]
        int[] len = new int[nums.length];
        // cnt[i]: maxCntOfLIS(nums[0..i]) ending with nums[i]
        int[] cnt = new int[nums.length];

        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                    else if (len[j] + 1 == len[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
        }

        int maxLen = 0;
        for (int l : len) {
            maxLen = Math.max(maxLen, l);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (len[i] == maxLen) res += cnt[i];
        }
        return res;
    }
}
