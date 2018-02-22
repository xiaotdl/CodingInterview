package _132_pattern;

/**
 * Created by Xiaotian on 2/22/18.
 */
public class Solution {
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int prevMin = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (prevMin == nums[j]) continue;
            for (int k = j + 1; k < n; k++) {
                if (prevMin < nums[k] && nums[k] < nums[j]) return true;
            }
            prevMin = Math.min(prevMin, nums[j]);
        }
        return false;
    }
}
