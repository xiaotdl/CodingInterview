package shortest_unsorted_continuous_subarray;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array
    // time: O(nlogn)
    // space: O(n)
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;

        int[] tmp = nums.clone();
        Arrays.sort(tmp);
        int l = 0;
        while (l < n && nums[l] == tmp[l]) l++;
        int r = n - 1;
        while (r > l && nums[r] == tmp[r]) r--;
        return r - l + 1;
    }
}

class SolutionII {
    // two pass
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int l = 0;
        int r = nums.length - 1;
        while (l < r && nums[l] <= nums[l + 1]) l++; // points to start of unsorted array
        if (l == r) return 0;
        while (r > l && nums[r] >= nums[r - 1]) r--; // points to end of unsorted array


        int max = Integer.MIN_VALUE; // max val of unsorted array
        int min = Integer.MAX_VALUE; // min val of unsorted array
        for (int i = l; i <= r; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        while (l >= 0 && min < nums[l]) l--;
        while (r < nums.length && nums[r] < max) r++;

        return r - l - 1;
    }
}
