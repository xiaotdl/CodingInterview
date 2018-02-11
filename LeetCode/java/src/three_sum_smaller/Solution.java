package three_sum_smaller;

import java.util.Arrays;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n^2)
    // space: O(1)
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1; // start
            int r = nums.length - 1; //end
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] < target) {
                    cnt += r - l; // all k will satisfy
                    l++;
                }
                else {
                    r--;
                }
            }
        }

        return cnt;
    }
}
