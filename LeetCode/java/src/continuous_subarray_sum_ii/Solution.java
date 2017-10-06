package continuous_subarray_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 10/5/17.
 */
public class Solution {
    // 用取反来解决循环数组问题
    // tag: ptr
    // time: O(n)
    // space: O(1)
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySumII(int[] A) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);

        int n = A.length;

        int total = 0;
        for (int num : A) {
            total += num;
        }

        //先找不循环情况下最大子数组
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

        //找最小子数组，数组总和减去最小子数组即为又循环情况下最大子数组
        currSum = 0;
        start = 0;
        end = 0;
        for (int i = 0; i < n; i++) {
            if (currSum > 0) {
                currSum = A[i];
                start = end = i;
            } else {
                currSum += A[i];
                end = i;
            }
            //若最小数组为整个数组（即所有元素为负），则返回第一种情况结果
            if (start == 0 && end == n-1) continue;
            if (total - currSum > max) {
                max = total - currSum;
                //为了防止出现最小子数组包含头尾而导致越界
                res.set(0, (end + 1) % n);
                res.set(1, (start - 1 + n) % n);
            }
        }

        return res;
    }
}
