package prev_permutation;

import java.util.*;

/**
 * Created by Xiaotian on 6/27/17.
 */
public class Solution {
    // algo: 2 for loops, 1 swap, 1 reverse
    // tag: array
    // time: O(n)
    // space: O(1)
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        if (nums == null || nums.size() <= 1) return nums;

        int n = nums.size();
        int x;
        for (x = n - 2; x >= 0 && nums.get(x) <= nums.get(x + 1); x--);
        if (x < 0) {
            reverse(nums, 0, n - 1);
            return nums;
        }
        int y;
        for (y = n - 1; nums.get(y) >= nums.get(x); y--);
        swap(nums, x, y);
        reverse(nums, x + 1, n - 1);
        return nums;
    }

    private void reverse(ArrayList<Integer> nums, int s, int e) {
        while (s < e) {
            int tmp = nums.get(s);
            nums.set(s, nums.get(e));
            nums.set(e, tmp);
            s++;
            e--;
        }
    }

    private void swap(ArrayList<Integer> nums, int x, int y) {
        int tmp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, tmp);
    }
}

