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
            int j = i + 1; // start
            int k = nums.length - 1; //end
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    cnt += k - j; // all k will satisfy
                    j++;
                }
                else {
                    k--;
                }
            }
        }

        return cnt;
    }
}
