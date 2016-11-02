package find_the_duplicate_number;

/**
 * Created by Xiaotian on 11/1/16.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogn), find duplicate in logn times, each time loop through n nums
    // space: O(1)
    public int findDuplicate(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int start = 1;
        int end = nums.length;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int cntLessThanMid = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    cntLessThanMid++;
                }
            }
            if (cntLessThanMid < mid) {
                // duplicate resides in rhs
                start = mid;
            }
            else {
                // duplicate resides in lhs
                end = mid;
            }
        }

        if (isDuplicate(start, nums)) {
            return start;
        }

        if (isDuplicate(end, nums)) {
            return end;
        }

        return -1;
    }

    boolean isDuplicate(int num, int[] nums) {
        for (int currNum : nums) {
            if (currNum == num) {
                return true;
            }
        }
        return false;
    }
}
