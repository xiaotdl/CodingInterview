package find_all_numbers_disappeared_in_an_array;

import java.util.*;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class Solution {
    // swap num to num - 1 location
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

class SolutionII {
    // O(n)&O(1) use input array to record occurence, +: not seen, -: seen
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int pos = Math.abs(nums[i]) - 1; // ptrs to matching index
            if (nums[pos] > 0) nums[pos] = -nums[pos];
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) res.add(i + 1);
        }
        return res;
    }
}
