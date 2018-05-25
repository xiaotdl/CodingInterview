package patching_array;

/**
 * Created by Xiaotian on 3/2/18.
 */
public class Solution {
    // Ref: https://leetcode.com/problems/patching-array/discuss/78488/Solution-+-explanation
    // tag: greedy
    // time: O(logn)
    // space: O(1)
    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int added = 0;
        int i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                added++;
            }
        }
        return added;
    }
}
