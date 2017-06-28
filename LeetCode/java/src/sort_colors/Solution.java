package sort_colors;

/**
 * Created by Xiaotian on 6/28/17.
 */
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int l = 0;
        int r = nums.length - 1;
        int i = l;
        while (i <= r) {
            if (nums[i] == 0) swap(nums, l++, i++);
            else if (nums[i] == 2) swap(nums, r--, i);
            else i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
