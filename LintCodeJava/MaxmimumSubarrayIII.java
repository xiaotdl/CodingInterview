import java.util.ArrayList;

public class MaxmimumSubarrayIII {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */

    // TODO
    // V1, O(k*n^2)
    // DP
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        if (nums == null || nums.size() == 0 || k > nums.size()) {
            return 0;
        }

        int len = nums.size();
        int[][] f = new int[k + 1][len];

        for (int i = 1; i < k + 1; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += nums.get(j);
            }
            f[i][i - 1] = sum;
        }

        for (int i = 1; i < len; i++) {
            f[1][i] = Math.max(f[1][i - 1] + nums.get(i), nums.get(i));
        }

        for (int i = 2; i < k + 1; i++) {
            for (int n = i; n < len; n++) {
                int max = f[i][n - 1] + nums.get(n);
                for (int j = i - 2; j < n; j++) {
                    max = Math.max(max, f[i - 1][j] + nums.get(n));
                }
                f[i][n] = max;
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = k - 1; i < len; i++){
            result = Math.max(result, f[k][i]);
        }
        return result;
    }
}

