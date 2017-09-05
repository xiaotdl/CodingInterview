package remove_duplicates_from_sorted_array_ii;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }
}
