package valid_triangle_number;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array, ptr
    // time: O(n^2)
    // space: O(1)
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if (nums == null || n <= 2) return 0;

        int cnt = 0;
        Arrays.sort(nums);
        for (int i = n - 1; i >= 2; i--) {
            int l = 0;
            int r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    cnt += r - l;
                    r--;
                }
                else {
                    l++;
                }
            }
        }
        return cnt;
    }
}
