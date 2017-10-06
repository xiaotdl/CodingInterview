package wiggle_sort;

/**
 * Created by Xiaotian on 10/5/17.
 */
public class Solution {
    // tag: sort
    // time: O(n)
    // space: O(1)
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 0 && nums[i - 1] < nums[i])
             || (i % 2 == 1 && nums[i - 1] > nums[i])) {
                swap(nums, i - 1, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
