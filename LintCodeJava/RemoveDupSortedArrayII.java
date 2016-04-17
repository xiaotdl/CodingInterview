public class RemoveDupSortedArrayII {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */

    // V1, O(n)
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums.length;
        }

        int size;
        int j = 0;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
                count = 1;
            } else {
                count++;
                if (count <= 2) {
                    j++;
                    nums[j] = nums[i];
                }
            }
        }

        size = j + 1;
        return size;
    }
}
