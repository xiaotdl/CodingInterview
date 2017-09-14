package non_decreasing_array;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // when disorder occurs, either change nums[i] or nums[i+1]
    // case1: nums[i-1] > nums[i+1]
    //      i-1  i  i+1         i-1  i  i+1
    //           -                   -   -
    //       -            ==>    -
    //               -
    // case2: nums[i-1] < nums[i+1]
    //      i-1  i  i+1         i-1  i  i+1
    //           -
    //               -    ==>        -   -
    //       -                   -
    // tag: array, greedy
    // time: O(n)
    // space: O(1)
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                cnt++;
                if (i != 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
                else {
                    nums[i] = nums[i + 1];
                }
                if (cnt > 1) return false;
            }
        }
        return cnt <= 1;
    }
}
