package maximum_sum_rectangle_in_a_2d_matrix;

import java.util.*;

/**
 * Created by Xiaotian on 3/19/18.
 */
public class Solution {
    // Ref: https://www.youtube.com/watch?v=yCQN096CwWM
    // 2d kadane
    // tag: dp
    // time: O(n^2*m)
    // space: O(m)
    public int[] maxSum(int matrix[][]){
        int m = matrix.length;
        int n = matrix[0].length;
        int sum[] = new int[m];
        int maxSum = Integer.MIN_VALUE;
        int maxL = -1, maxR = -1, maxU = -1, maxD = -1;
        for (int l = 0; l < n; l++) {
            Arrays.fill(sum, 0);
            for (int r = l; r < n; r++) {
                for (int i = 0; i < m; i++) sum[i] += matrix[i][r];
                int[] kadaneRes = kadane(sum);
                int currMaxSum = kadaneRes[0];
                int currMaxU = kadaneRes[1];
                int currMaxD = kadaneRes[2];
                if (currMaxSum > maxSum) {
                    maxSum = currMaxSum;
                    maxL = l;
                    maxR = r;
                    maxU = currMaxU;
                    maxD = currMaxD;
                }
            }
        }
        return new int[]{maxSum, maxL, maxR, maxU, maxD};
    }

    public int[] kadane(int[] A) {
        int maxEndingHere = A[0];
        int maxSoFar = A[0];
        int l = 0;
        int r = 0;
        int maxL = 0;
        int maxR = 0;
        for (int i = 1; i < A.length; i++) {
            if (maxEndingHere < 0) {
                l = i;
                r = i;
                maxEndingHere = A[i];
            }
            else {
                r = i;
                maxEndingHere += A[i];
            }
            if (maxEndingHere > maxSoFar && maxEndingHere <= 10) {
                maxSoFar = maxEndingHere;
                maxL = l;
                maxR = r;
            }
        }
        return new int[]{maxSoFar, maxL, maxR};
    }

    public static void main(String[] args) {
        int input[][] = {
                { 2,  1, -3, -4,  5},
                { 0,  6,  3,  4,  1},
                { 2, -2, -1,  4, -5},
                {-3,  3,  1,  0,  3}
        };
        System.out.println(Arrays.toString(new Solution().maxSum(input)));
    }
}

