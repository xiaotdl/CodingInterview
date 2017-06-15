package four_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 6/14/17.
 */
public class Solution {
    // tag: hash
    // time: O(n^2)
    // space: O(n^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> twoSum = new HashMap<>();

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                twoSum.put(sum, twoSum.containsKey(sum) ? twoSum.get(sum) + 1 : 1);
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int target = -(A[i] + B[j]);
                if (twoSum.containsKey(target)) {
                    res += twoSum.get(target);
                }
            }
        }

        return res;
    }
}
