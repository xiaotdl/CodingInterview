package three_sum_closest;

import java.util.Arrays;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n^2)
    // space: O(1)
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int threeSum = nums[i] + nums[start] + nums[end];
                if (threeSum == target) {
                    return target;
                }
                else if (threeSum < target) {
                    closest = Math.abs(target - closest) <= Math.abs(target - threeSum) ? closest : threeSum;
                    start++;
                }
                else {
                    closest = Math.abs(target - closest) <= Math.abs(target - threeSum) ? closest : threeSum;
                    end--;
                }
            }
        }

        return closest;
    }
}
