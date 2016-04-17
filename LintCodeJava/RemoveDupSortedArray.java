public class RemoveDupSortedArray {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */

    // V1, O(n)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int size;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }

        size = j + 1;
        return size;
    }
}
