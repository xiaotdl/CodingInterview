package move_zeroes;

/**
 * Created by Xiaotian on 6/13/17.
 */
// tag: ptr
// time: O(n)
// space: O(1)
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // shift non-zero numbers to the left
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        // fill out right with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
