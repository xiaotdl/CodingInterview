package search_in_rotated_sorted_array_ii;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: binary search
    // time: O(n), worst case, all duplicates
    // space: O(1)
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
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
                    else if (nums[mid] < nums[end]) {
                        end = mid;
                    }
                    else {
                        end -= 1;
                    }
                }
                else {
                    return true;
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
                    else if (nums[mid] < nums[end]) {
                        end = mid;
                    }
                    else {
                        end -= 1;
                    }
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }

        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }

        return false;
    }
}
