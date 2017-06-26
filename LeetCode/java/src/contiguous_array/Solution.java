package contiguous_array;

import java.util.*;

/**
 * Created by Xiaotian on 6/25/17.
 */
public class Solution {
    // tag: hash, prefix sum
    // time: O(n)
    // space: O(n)
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> m = new HashMap<>(); // sum2index

        int sum = 0; // prefixSum
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (sum == 0) {
                max = Math.max(max, i + 1);
            }
            else if (m.containsKey(sum)) {
                max = Math.max(max, i - m.get(sum));
            }
            m.put(sum, m.containsKey(sum) ? m.get(sum) : i);
        }

        return max;
    }
}

