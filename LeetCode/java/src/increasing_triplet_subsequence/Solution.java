package increasing_triplet_subsequence;

/**
 * Created by Xiaotian on 2/25/18.
 */
class Solution {
    // LIS when len is 3
    // tag: array
    // time: O(n)
    // space: O(1)
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;

        int[] minLast = new int[2];
        for (int i = 0; i < 2; i++) minLast[i] = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= minLast[0]) {
                minLast[0] = num;
            }
            else if (num <= minLast[1]) {
                minLast[1] = num;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
