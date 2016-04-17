public class MaximumProductSubarray {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */

    // V1, O(n), O(n)
    // DP
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] min = new int[nums.length];
        int[] max = new int[nums.length];

        min[0] = max[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }
            result = Math.max(result, max[i]);
        }

        return result;
    }

    // V2, O(n), O(1)
    // DP
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = nums[0], max = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i], max * nums[i]);
                min = Math.min(nums[i], min * nums[i]);
            } else if (nums[i] < 0) {
                int last_max = max;
                max = Math.max(nums[i], min * nums[i]);
                min = Math.min(nums[i], last_max * nums[i]);
            } else {
                min = max = 0;
            }
            result = Math.max(result, max);
        }

        return result;
    }
}
