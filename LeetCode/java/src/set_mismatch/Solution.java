package set_mismatch;

/**
 * Created by Xiaotian on 3/15/18.
 */
public class Solution {
    // use input array to record occurence, A[i] == i + 1: seen, otherwise: not seen
    // tag: array
    // time: O(n)
    // space: O(1)
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];

        int n = nums.length;
        int i = 0;
        while (i < n) {
            int pos = nums[i] - 1;
            if (nums[pos] != nums[i]) swap(nums, i, pos);
            else i++;
        }

        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                res[0] = nums[i];
                res[1] = i + 1;
                break;
            }
        }
        return res;
    }

    private void swap(int[] nums, int i , int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
