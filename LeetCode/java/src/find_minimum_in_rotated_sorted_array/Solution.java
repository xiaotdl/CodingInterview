package find_minimum_in_rotated_sorted_array;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: array, binary search
    // time: O(logn)
    // space: O(1)
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            }
            else if (nums[mid] <= nums[end]) {
                end = mid;
            }
        }

        return Math.min(nums[start], nums[end]);
    }
}
