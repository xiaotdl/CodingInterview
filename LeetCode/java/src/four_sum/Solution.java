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


class SolutionII {
    // tag: ptr
    // time: O(n^3)
    // space: O(1)
    /*
     * @param numbers: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip dups

            List<List<Integer>> threes = threeSum(nums, i + 1, nums.length - 1, target - nums[i]);
            if (threes.size() == 0) continue;

            for (List<Integer> three : threes) {
                res.add(new ArrayList<>(Arrays.asList(nums[i], three.get(0), three.get(1), three.get(2))));
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums, int l, int r, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = l; i <= r - 2; i++) {
            if (i > l && nums[i] == nums[i - 1]) continue; // skip dups

            List<List<Integer>> twos = twoSum(nums, i + 1, nums.length - 1, target-nums[i]);
            if (twos.size() == 0) continue;

            for (List<Integer> two : twos) {
                res.add(new ArrayList<>(Arrays.asList(nums[i], two.get(0), two.get(1))));
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int l , int r, int target) {
        List<List<Integer>> res = new ArrayList<>();
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                res.add(new ArrayList<>(Arrays.asList(nums[l], nums[r])));
                l++;
                r--;
                while (l < r && nums[l] == nums[l - 1]) l++; // skip dups
                while (l < r && nums[r] == nums[r + 1]) r--; // skip dups
            }
            else if (nums[l] + nums[r] > target) {
                r--;
            }
            else {
                l++;
            }
        }
        return res;
    }
}

class SolutionIII {
    // Ref: https://leetcode.com/problems/4sum/discuss/8761/Any-better-solution-than-O(n3)/9843
    // save two sum in treeset O(n^2*logn)
    // O(n^2) iterate two numbers and use two_sum_treeset to check if they sum to target
    // tag: treeset
    // time: O(n^2*logn)
    // space: O(n^2)
}
