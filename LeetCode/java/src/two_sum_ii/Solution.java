package two_sum_ii;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogn)
    // space: O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(nums, i + 1, nums.length - 1, target - nums[i]);
            if (index != -1) {
                return new int[]{i+1, index+1};
            }
        }

        return null;
    }

    int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        int start = startIndex;
        int end = endIndex;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                return mid;
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


    // Now if the input array is sorted, the n-by-n summation matrix will have the following properties:
    // 1. Integers in each row are sorted in ascending order from left to right.
    // 2. Integers in each column are sorted in ascending order from top to bottom.
    //int[]{1,2,3,4}
    // i = 0, j = n - 1
    //   0   1   2   3
    // 0     1+2 1+3 1+4 <= (i,j) starting point
    // 1         2+3 2+4 <= l++
    // 2          /\ 3+4
    // 3          ||
    //           r--
    // tag: two pointers
    // time: O(n)
    // space: O(1)
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        int i = 0;
        int j = numbers.length - 1;
        while (i < numbers.length && j >= 0) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            }
            else if (numbers[i] + numbers[j] < target) {
                i++;
            }
            else {
                return new int[]{i+1, j+1};
            }
        }

        return null;
    }

}


