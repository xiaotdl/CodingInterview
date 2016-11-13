package search_insert_position;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (target <= nums[0]) {
            return 0;
        }

        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        return binarySearchFirstEqualOrLarger(nums, target);
    }

    int binarySearchFirstEqualOrLarger(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        }
        if (nums[end] >= target) {
            return end;
        }
        return -1;
    }
}
