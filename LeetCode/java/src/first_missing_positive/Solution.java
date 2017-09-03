package first_missing_positive;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // store i+1 in nums[i]
        for (int i = 0; i < n; i++) {
            while (0 < nums[i] && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] nums, int i , int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
