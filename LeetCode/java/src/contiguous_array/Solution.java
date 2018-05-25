package contiguous_array;

import java.util.*;

/**
 * Created by Xiaotian on 6/25/17.
 */
class Solution {
    // credit: https://leetcode.com/problems/contiguous-array/solution/
    // tag: hash, prefix sum
    // time: O(n)
    // space: O(n)
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> m = new HashMap<>(); // prefixSum2idx

        int prefixSum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += (nums[i] == 0 ? -1 : 1);
            if (prefixSum == 0) {
                maxLen = Math.max(maxLen, i + 1);
            }
            else if (m.containsKey(prefixSum)) {
                maxLen = Math.max(maxLen, i - m.get(prefixSum));
            }
            m.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }
}
