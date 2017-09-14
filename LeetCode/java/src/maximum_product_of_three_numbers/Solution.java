package maximum_product_of_three_numbers;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int maximumProduct(int[] nums) {
        if (nums.length < 3) return 0;

        int max1, max2, max3, min1, min2;
        max1 = max2 = max3 = Integer.MIN_VALUE;
        min1 = min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            }
            else if (num > max2) {
                max3 = max2;
                max2 = num;
            }
            else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            }
            else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }
}