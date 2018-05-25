package remove_duplicates_from_sorted_array;

/**
 * Created by Xiaotian on 8/19/17.
 */
public class Solution {
    // tag: array, ptr
    // time: O(n)
    // space: O(1)
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int i = 1;
        int j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[j - 1]) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }
}

class SolutionII {
    // Similar to partition
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (j > 0 && nums[j] == nums[j - 1]) continue;
            nums[i] = nums[j];
            i++;
        }
        return i;
    }
}
