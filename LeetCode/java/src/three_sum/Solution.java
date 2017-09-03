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
