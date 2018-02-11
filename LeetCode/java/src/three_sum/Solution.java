package three_sum;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n^2)
    // space: O(1)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length < 3) return results;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // avoid dup result
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] == target) {
                    ArrayList<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[start]);
                    result.add(nums[end]);
                    results.add(result);
                    start++;
                    end--;
                    // avoid dup result
                    while (start < end && nums[start] == nums[start - 1]) start++;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                }
                else if (nums[start] + nums[end] < target) {
                    start++;
                }
                else {
                    end--;
                }

            }
        }

        return results;
    }
}

class SolutionII {
    // Same as Solution
    /*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // avoid duplicates

            List<List<Integer>> twos = twoSum(nums, i + 1, nums.length - 1, -nums[i]);
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