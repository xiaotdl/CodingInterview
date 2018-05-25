package continuous_subarray_sum;

import java.util.*;

/**
 * Created by Xiaotian on 6/25/17.
 */
public class Solution {
    // credit: https://leetcode.com/problems/continuous-subarray-sum/solution/
    // Leetcode
    // tag: hash, prefix sum
    // time: O(n)
    // space: O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        Map<Integer, Integer> m = new HashMap<>(); //prefixSum[%k]2idx
        m.put(0, -1);

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) prefixSum %= k;

            if (m.containsKey(prefixSum) && i - m.get(prefixSum) >= 2) return true;

            m.putIfAbsent(prefixSum, i);
        }
        return false;
    }
}

class SolutionII {
    // Lintcode
    // tag: ptr
    // time: O(n)
    // space: O(1)
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] A) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);

        int n = A.length;
        int max = Integer.MIN_VALUE;
        int currSum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (currSum < 0) {
                currSum = A[i];
                start = end = i;
            } else {
                currSum += A[i];
                end = i;
            }
            if (currSum > max) {
                max = currSum;
                res.set(0, start);
                res.set(1, end);
            }
        }
        return res;
    }
}
