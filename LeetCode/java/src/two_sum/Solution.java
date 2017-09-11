package two_sum;

import java.util.HashMap;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
    // tag: brutal force
    // time: O(n^2)
    // space: O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    // tag: hash
    // time: O(n)
    // space: O(n)
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(target - nums[i])) {
                return new int[]{hm.get(target - nums[i]), i};
            }
            hm.put(nums[i], i);
        }

        return null;
    }
}
