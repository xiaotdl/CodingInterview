package find_all_duplicates_in_an_array;

import java.util.*;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class Solution {
    // credits: https://discuss.leetcode.com/topic/64735/java-simple-solution/2
    // when find a number i, flip the number at position i-1 to negative.
    // if the number at position i-1 is already negative, i is the number that occurs twice.
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int j = Math.abs(num) - 1;
            if (nums[j] < 0) {
                res.add(Math.abs(j + 1));
            }
            nums[j] = -nums[j];
        }
        // revert back to orig array
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(new Solution().findDuplicates(nums));
    }
}
