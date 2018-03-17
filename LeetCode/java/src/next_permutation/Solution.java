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

class SolutionII {
    // Ref: https://leetcode.com/problems/next-permutation/solution/
    // tag: array, permutation
    // time: O(n)
    // space: O(1)
    /*
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;

        // find first declining number nums[i] from backwards
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i < 0) {
            reverse(nums, 0, nums.length - 1);
            return nums;
        }

        // find first number num[j] that's greater than nums[i] from backwards
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) j--;

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
        return nums;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
