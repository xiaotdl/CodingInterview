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

class SolutionII {
    // tag: array
    // time: O(n)
    // space: O(1)
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int pos = 0;
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) { // modify either i - 1 or i
                pos = i;
                cnt++;
            }
        }

        if (cnt == 0) return true;
        if (cnt > 1) return false;
        return pos == 1
                || pos == nums.length - 1
                || nums[pos - 1] <= nums[pos + 1] // modify i
                || nums[pos - 2] <= nums[pos];    // modify i - 1
    }
}
