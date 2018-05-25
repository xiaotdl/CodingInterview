package max_sum_of_rectangular_no_larger_than_k;

import java.util.*;

/**
 * Created by Xiaotian on 3/19/18.
 */
public class Solution {
    // similar to max_sum_rectangle_in_a_2d_matrix
    // tag: matrix
    // time: O(n^2*m^2)
    // space: O(m)
    public int maxSumSubmatrix(int matrix[][], int k){
        int m = matrix.length;
        int n = matrix[0].length;
        int sum[] = new int[m];
        int maxSum = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            Arrays.fill(sum, 0);
            for (int r = l; r < n; r++) {
                for (int i = 0; i < m; i++) sum[i] += matrix[i][r];
                int currMaxSum = maxSumLessOrEqThanK(sum, k);
                if (currMaxSum > maxSum) {
                    maxSum = currMaxSum;
                }
            }
        }
        return maxSum != Integer.MIN_VALUE ? maxSum : -1;
    }

    public int maxSumLessOrEqThanK(int[] A, int k) {
        int[] prefixSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) prefixSum[i + 1] = prefixSum[i] + A[i];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int sum = prefixSum[j + 1] - prefixSum[i];
                if (sum <= k && sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }
}

