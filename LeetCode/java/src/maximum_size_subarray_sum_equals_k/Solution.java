package maximum_size_subarray_sum_equals_k;

import java.util.*;

/**
 * Created by Xiaotian on 6/17/17.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(n)
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> m = new HashMap<>(); // prefixSum2index
        int maxLen = 0;
        int prefixSum = 0; // sum from nums[0] to nums[i]
        for (int i = 0; i< nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) {
                maxLen = i + 1;
            }
            else if (m.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - m.get(prefixSum - k));
            }
            if (!m.containsKey(prefixSum)) {
                m.put(prefixSum, i);
            }
        }
        return maxLen;
    }
}
