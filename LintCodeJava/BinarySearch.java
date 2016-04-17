public class BinarySearch {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */

    // V1, O(logn)
    // Binary Search, Iteration
    // Keypoints:
    //      1. start + 1 < end
    //      2. start + (end - start) / 2
    //      3. nums[mid] ==, <, >
    //      4. nums[start] nums[end] ? target
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 3, 9, 11};
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 9));
    }
}
