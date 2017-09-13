package array_partition_i;

import java.util.*;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // credits: https://discuss.leetcode.com/topic/87206/java-solution-sorting-and-rough-proof-of-algorithm
    // tag: array
    // time: O(n)
    // space: O(1)
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
