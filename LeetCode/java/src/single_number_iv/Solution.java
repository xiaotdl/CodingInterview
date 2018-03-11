package single_number_iv;

/**
 * Created by Xiaotian on 3/7/18.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == nums[m - 1]) {
                if ((m - l + 1) % 2 == 1) {
                    r = m - 2;
                }
                else {
                    l = m + 1;
                }
            }
            else if (nums[m] == nums[m + 1]) {
                if ((r - m + 1) % 2 == 1) {
                    l = m + 2;
                }
                else {
                    r = m - 1;
                }
            }
            else {
                return nums[m];
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getSingleNumber(new int[]{2, 1, 1}));
    }
}

class SolutionII {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == nums[m - 1]) {
                if (m % 2 == 1) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
            else if (nums[m] == nums[m + 1]) {
                if (m % 2 == 0) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
            else {
                return nums[m];
            }
        }
        // skip l, r checking as l, r will never point to the single number
        return -1;
    }
}
