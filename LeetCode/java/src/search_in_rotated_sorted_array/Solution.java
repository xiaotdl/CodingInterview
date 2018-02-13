package search_in_rotated_sorted_array;

/**
 * Created by Xiaotian on 11/12/16.
 */
public class Solution {
    // tag: array, binary search
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

class SolutionII {
    // tag: array, binary search
    // time: O(logn)
    // space: O(1)
    /*
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int l = 0;
        int r = A.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (A[m] == target) {
                return m;
            }

            if (A[l] < A[m]) { // 确定m的位置是上边还是下边
                if (A[l] <= target && target <= A[m]) { // 确定target是不是在可描述的一小截内
                    r = m;
                }
                else {
                    l = m;
                }
            }
            else {
                if (A[m] <= target && target <= A[r]) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
        }
        if (A[l] == target) return l;
        if (A[r] == target) return r;
        return -1;
    }
}
