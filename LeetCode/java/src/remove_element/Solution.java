package remove_element;

/**
 * Created by Xiaotian on 8/19/17.
 */
public class Solution {
    // tag: array, ptr
    // time: O(n)
    // space: O(1)
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }
}
