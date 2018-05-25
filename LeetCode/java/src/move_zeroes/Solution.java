package move_zeroes;

import java.util.Arrays;

/**
 * Created by Xiaotian on 6/13/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // shift non-zero numbers to the left
        int i = 0;
        for (int num : nums) {
            if (num != 0) nums[i++] = num;
        }

        // fill out left numbers in the right with zeros
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
