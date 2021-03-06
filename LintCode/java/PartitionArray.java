public class PartitionArray {
    /**
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */

    // V1, O(n), O(1)
    // Two pointers
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            while (i <= j && nums[i] < k) { i++; }
            while (i <= j && nums[j] >= k) { j--; }
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }
}

