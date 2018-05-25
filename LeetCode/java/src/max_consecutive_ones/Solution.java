package max_consecutive_ones;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int currOnes = 0;
        for (int num : nums) {
            if (num == 1) {
                currOnes++;
                max = Math.max(max, currOnes);
            }
            else {
                currOnes = 0;
            }
        }
        return max;
    }
}
