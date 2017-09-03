package search_range;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: array, binary search
    // time: O(nlogn)
    // space: O(1)
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int s = binary_search_first_equal(nums, target);
        int e = binary_search_last_equal(nums, target);
        return new int[]{s, e};
    }

    private int binary_search_first_equal(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s + 1 < e) {
            int m = s + (e - s) / 2;
            if (nums[m] >= target) {
                e = m;
            }
            else {
                s = m;
            }
        }
        if (nums[s] == target) {
            return s;
        }
        if (nums[e] == target) {
            return e;
        }
        return -1;
    }

    private int binary_search_last_equal(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s + 1 < e) {
            int m = s + (e - s) / 2;
            if (nums[m] <= target) {
                s = m;
            }
            else {
                e = m;
            }
        }
        if (nums[e] == target) {
            return e;
        }
        if (nums[s] == target) {
            return s;
        }
        return -1;
    }
}
