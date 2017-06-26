package continuous_subarray_sum;

import java.util.*;

/**
 * Created by Xiaotian on 6/25/17.
 */
public class Solution {
    // tag: hash, prefix sum
    // time: O(n)
    // space: O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // modulusOfSum2index
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            if (sum == 0 && i >= 1) {
                return true;
            }
            else if (map.containsKey(sum) && i - map.get(sum) >= 2) {
                return true;
            }
            map.put(sum, map.containsKey(sum) ? map.get(sum) : i);
        }
        return false;
    }
}
