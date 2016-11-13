package search_in_rotated_sorted_array;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target > nums[end]) {
                if (nums[mid] > target) {
                    end = mid;
                }
                else if (nums[mid] < target) {
                    if (nums[mid] > nums[end]) {
                        start = mid;
                    }
                    else if (nums[mid] <= nums[end]) {
                        end = mid;
                    }
                }
                else {
                    return mid;
                }
            }
            else if (target < nums[end]) {
                if (nums[mid] < target) {
                    start = mid;
                }
                else if (nums[mid] > target) {
                    if (nums[mid] > nums[end]) {
                        start = mid;
                    }
                    else if (nums[mid] <= nums[end]) {
                        end = mid;
                    }
                }
                else {
                    return mid;
                }
            }
            else {
                return end;
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        return -1;
    }
}
