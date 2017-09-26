package maximum_average_subarray_ii;

/**
 * Created by Xiaotian on 9/26/17.
 */
public class Solution {
    // tag: binary search
    // time: O(nlog(max - min))
    // space: O(1)
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        double eps = 1e-8;
        double l = min;
        double r = max;
        while (r - l > eps) {
            double m = l + (r - l) / 2;

            if (existsLargerAvg(nums, m, k)) {
                l = m;
            } else {
                r = m;
            }
        }

        return l;
    }

    private boolean existsLargerAvg(int nums[], double currMaxAvg, int k) {
        double sum = 0;
        double prevKSum = 0;
        double minPrevKSum = 0; // minSum from 0 .. up to (i - k)
        for (int i = 0; i < k; i++) {
            sum += nums[i] - currMaxAvg;
        }
        if (sum > 0) {
            return true;
        }
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - currMaxAvg;
            prevKSum += nums[i - k] - currMaxAvg;
            minPrevKSum = Math.min(minPrevKSum, prevKSum);
            if (sum > minPrevKSum) {
                return true;
            }
        }
        return false;
    }
}

