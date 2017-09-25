package find_peak_element;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0 ) return -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }
}

class SolutionII {
    // tag: array, binary search
    // time: O(logn)
    // space: O(1)
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        if ((mid == 0 || nums[mid - 1] < nums[mid])
                && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
            return mid;
        }
        // left elements must have a peak
        else if (mid > 0 && nums[mid - 1] > nums[mid]) {
            return helper(nums, start, mid - 1);
        }
        // right elements must have a peak
        else {
            return helper(nums, mid + 1, end);
        }
    }
}

class SolutionIII {
    // Same as SolutionII
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // left side must have a peak
            if (nums[mid - 1] > nums[mid]) {
                end = mid;
            }
            // right side must have a peak
            else {
                start = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        }
        return end;
    }
}
