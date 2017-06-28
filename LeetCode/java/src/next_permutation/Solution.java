package next_permutation;

/**
 * Created by Xiaotian on 6/27/17.
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        // nums => A[] + nums[x] + B[], x => first num before a non-increasing seq
        // next => A[] + nums[y] + B'[], y => smallest num that is larger than nums[x] in the non-increasing seq
        // target:
        //   - A[] as long as possible
        //   - nums[y] > nums[x]
        //   - B[]/B'[] are both non-increasing sequence
        // algo: 2 for loops, 1 swap, 1 reverse
        // tag: array
        // time: O(n)
        // space: O(1)
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        int x;
        for (x = n - 2; x >= 0 && nums[x] >= nums[x + 1]; x--);
        if (x < 0) {
            reverse(nums, 0, n - 1);
            return;
        }
        int y;
        for (y = n - 1; nums[y] <= nums[x]; y--);
        swap(nums, x, y);
        reverse(nums, x + 1, n - 1);
    }

    private void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
            s++;
            e--;
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
