package missing_number;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: bit manipulation
    // time: O(n)
    // space: O(1)
    public int missingNumber(int[] nums) {
        // a^b^b =a
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= (i + 1) ^ nums[i];
        }
        return res;
    }
}
