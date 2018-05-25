package random_pick_index;

import java.util.*;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // tag: reservor sampling
    // time: O(n)
    // space: O(1)
    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int pick = -1;
        int targetCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            targetCnt++;
            if (random.nextInt(targetCnt) == 0) {
                pick = i;
            }
        }
        return pick;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
