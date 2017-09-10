package major_element;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // Boyer-Moore Majority Vote algorithm
    // tag: array
    // time: O(n)
    // space: O(1)
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count = 1;
            }
            else if (nums[i] == major) count++;
            else count--;

        }
        return major;
    }
}
