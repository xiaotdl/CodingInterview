import java.util.ArrayList;

public class RecoverRotatedSortedArray {
    /**
     * @param nums: The rotated sorted array
     * @return: void
     */

    // V1, O(n), O(1)
    // Reverse
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return;
        }

        int i;
        for (i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                break;
            }
        }

        reverse(nums, 0, i);
        reverse(nums, i + 1, nums.size() - 1);
        reverse(nums, 0, nums.size() - 1);
    }

    private void reverse (ArrayList<Integer> nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
}
