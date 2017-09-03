package four_sum;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n^3)
    // space: O(1)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) return results;

        Arrays.sort(nums);
        if (4*nums[0] > target || 4*nums[nums.length - 1] < target) return results;

        for (int i = 0; i < nums.length - 3; i++) {
            // skip duplicates
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                // skip duplicates
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;

                int k = j + 1; // start
                int l = nums.length - 1; //end
                while (k < l) {
                    int fourSum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (fourSum == target) {
                        ArrayList<Integer> result = new ArrayList<Integer>();
                        result.add(nums[i]);
                        result.add(nums[j]);
                        result.add(nums[k]);
                        result.add(nums[l]);
                        results.add(result);
                        k++;
                        l--;
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    }
                    else if (fourSum < target) {
                        k++;
                    }
                    else {
                        l--;
                    }
                }
            }
        }

        return results;
    }
}

