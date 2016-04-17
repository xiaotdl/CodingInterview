public class SortColors {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */

    // V1, O(n), O(1)
    // Similar to two ptrs
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int start = 0;
        int end = nums.length - 1;
        int i = 0;
        while (i <= end) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[start];
                nums[start] = tmp;
                i++;
                start++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                int tmp = nums[i];
                nums[i] = nums[end];
                nums[end] = tmp;
                end--;
            }
        }

        return;
    }
}

