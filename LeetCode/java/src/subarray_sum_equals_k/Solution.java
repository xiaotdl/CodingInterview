package subarray_sum_equals_k;

import java.util.*;

/**
 * Created by Xiaotian on 6/20/17.
 */
public class Solution {
    // tag: array, hash
    // time: O(n)
    // space: O(1)
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> m = new HashMap<>(); //prefixSum2occurrence
        int res = 0;
        int sum = 0; // prefixSum
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                res++;
            }
            if (m.containsKey(sum - k)) {
                res += m.get(sum - k);
            }
            m.put(sum, m.containsKey(sum) ? m.get(sum) + 1 : 1);
        }
        return res;
    }
}
