package single_number_iii;

import java.util.*;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    // XOR
    // Divide nums into 2 groups => 2*n1 + a, 2*n2 + b
    // a ^ b != 0, condition => rightmost one bit that one set, the other not
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }

        int lastBit = xor - (xor & (xor - 1));
        int group0 = 0, group1 = 0;
        for (int i = 0; i < A.length; i++) {
            if ((lastBit & A[i]) == 0) {
                group0 ^= A[i];
            } else {
                group1 ^= A[i];
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(group0);
        result.add(group1);
        return result;
    }
}

